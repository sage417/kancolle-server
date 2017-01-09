package com.kancolle.server.service.battle.shelling.template;

import com.google.common.math.DoubleMath;
import com.google.common.math.IntMath;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.battle.SlotItemInfo;
import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.UnderSeaShip;
import com.kancolle.server.model.po.slotitem.AbstractSlotItem;
import com.kancolle.server.service.battle.formation.FormationSystem;
import com.kancolle.server.service.battle.course.CourseEnum;
import com.kancolle.server.service.battle.shelling.apply.BattleContextApply;
import com.kancolle.server.utils.logic.battle.BattleContextUtils;
import com.kancolle.server.utils.logic.ship.ShipFilter;
import com.kancolle.server.utils.logic.ship.ShipUtils;
import com.kancolle.server.utils.logic.slot.SlotItemUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.List;

import static com.google.common.collect.Iterables.getLast;

@Service
public class UnderSeaShipShellingSystem extends AbstractShellingTemplate<UnderSeaShip, MemberShip> {

    @Autowired
    @Qualifier("underSeaBattleContextApply")
    private BattleContextApply apply;

    @Override
    protected void prepareContext(final BattleContext context) {
        context.setApply(apply);
        context.setEnemyNormalShips(context.getAliveMemberNormalShips());
        context.setEnemySSShips(context.getAliveMemberSSShips());
    }

    @Override
    protected int[] generateActualDamage(MemberShip defendShip, int[] damages, BattleContext context) {
        for (int i = 0; i < damages.length; i++) {
            int damage = damages[i];
            int nowHp = defendShip.getNowHp();
            if (damage >= nowHp) {
                damages[i] = destroyAugmenting(nowHp);
            }
        }
        return damages;
    }

    /*击沉保护 */
    private int destroyAugmenting(final int nowHp) {
        // 当前血量20%~50%浮动
        return RandomUtils.nextInt(nowHp / 5, nowHp / 2 + 1);
    }

    @Override
    protected void callbackAfterDamage(final UnderSeaShip attackShip, final MemberShip defendShip, final int[] actualDamages, final int[] damages, final BattleContext context) {
        super.callbackAfterDamage(attackShip, defendShip, actualDamages, damages, context);
    }

    private void generateTaiSenDamageList(final UnderSeaShip attackShip, final MemberShip defendShip, final BattleContext context) {
    }


    @Override
    protected final double shipHoumRatios(final UnderSeaShip attackShip, final BattleContext context) {
        return HIT_BASE_RADIOS + (HIT_UNDERSEA_RADIOS + shipHoumRatios(attackShip)) * getHoumFormationFactor(context);
    }

    @Override
    protected double shipKaihiRatio(final MemberShip ship, final BattleContext context) {

        final int cond = ship.getCond();

        final int formation = BattleContextUtils.getMemberFormation(context);

        return houkThreshold(ship) * getKaihiFormationFactor(formation) * getKaihiCondFactor(cond) * getKaihiFuelFactor(ship);
    }

    /**
     * 状态对回避值的因子
     *
     * @param cond
     * @return
     */
    private double getKaihiCondFactor(final int cond) {
        if (cond < MemberShip.BAD_COND) {
            return 0.5d;
        } else if (cond < MemberShip.WARN_COND) {
            return 0.75d;
        } else if (cond < MemberShip.GOOD_COND) {
            return 1d;
        } else {
            return 1.8d;
        }
    }

    /**
     * 燃料对回避值的因子
     *
     * @param ship
     * @return
     */
    private double getKaihiFuelFactor(final MemberShip ship) {
        int maxFuel = ship.getShip().getFuelMax();
        int consumeFuel = maxFuel - ship.getFuel();
        int consume = DoubleMath.roundToInt(Math.floor(100d * consumeFuel / maxFuel), RoundingMode.HALF_DOWN);
        return IntMath.mod(consume, 20) * 0.16d;
    }

    @Override
    public int[] generateHougekiDamageList(final UnderSeaShip attackShip, final MemberShip defendShip, final int attackType, final int[] criticals, final BattleContext context) {
        switch (attackType) {
            case ATTACK_TYPE_NORMAL:
            case ATTACK_TYPE_EXPOSEARMOR:
            case ATTACK_TYPE_MAIN:
            case ATTACK_TYPE_RADAR:
            case ATTACK_TYPE_SECONDARY:
                final int hougAfterThreshold = attackValue(attackShip, defendShip, context);
                final int damageValue = damageValue(hougAfterThreshold, defendShip);
                return new int[]{damageValue};
            case ATTACK_TYPE_DOUBLE:
                return new int[]{0, 0};
            default:
                throw new IllegalArgumentException("attack type error");
        }
    }

    /**
     * 基本攻擊力計算：
     * <p>
     * 非航母攻擊：（對地攻擊除外）
     * 基本攻撃力 = 火力 + 改修補正(砲擊) + 聯合艦隊補正(註) + 5
     * <p>
     * 航母攻擊：
     * 基本攻撃力 = [55 +(火力 + [爆装×1.3]+ 雷装 + 改修補正(砲擊) + 聯合艦隊補正(註) )×1.5]
     * <p>
     * 以上公式中中括號需要向下取整
     * <p>
     * 只要航母能攻擊（不罰站），攻撃力便跟搭載數無關
     * 即使某一格攻擊機數目為0，也不會影響砲擊戰攻擊力（該艦攻艦爆的爆裝和雷裝仍會被計算）
     * <p>
     * 註：
     * 對於陸上敵人，計算傷害時雷裝無效（即一律當成0雷裝來計算）
     *
     * @param ship
     * @return
     */
    private int shellingBaiscHoug(final IShip ship) {
        //TODO 联合舰队基本攻击力补正
        //TODO 改修装备攻击力补正
        final int shipHoug = ship.getShipKaryoku();

        if (ShipFilter.carrierFilter.test(ship)) {
            final int raiSou = ship.getShipRaisou();
            final int baKu = ShipUtils.getBakuValue(ship);
            return (shipHoug + raiSou + baKu * 13 / 10) * 3 / 2 + 55;
        } else {
            return shipHoug + 5;
        }
    }

    /**
     * 基本攻撃力 = 2 x √(艦娘婐裝對潛) + 裝備對潛 × 1.5 + 改修補正(對潛) + 攻撃模式補正
     * 攻撃模式補正：艦載機對潛系數為8，深水炸彈對潛系數為13
     * <p>
     * 以下類型的裝備在計算反潛攻擊力時其對潛值將會看成是裝備對潛來計算：
     * 水聽、爆雷、水上爆撃機、艦上攻撃機、艦上爆撃機、對潜哨戒機、カ号観測機
     * <p>
     * 以下類型的裝備對反潛攻擊力沒有任何影響：
     * 水上偵察機、電探、小口徑主砲
     *
     * @param ship
     * @return
     */
    private int antiSubmarineBasicHoug(final MemberShip ship) {
        //TODO 联合舰队基本攻击力补正
        //TODO 修改装备攻击路补正
        return 0;
    }


    // 攻擊力 =  [ [ { 基本攻撃力 × 閾值前補正 + 輕巡適型砲補正 } ]  ×  徹甲彈特效補正 ×  PT小鬼群補正 ]  ×  暴擊補正 ]  × 閾值後補正
    private int attackValue(final UnderSeaShip attackShip, final MemberShip defendShip, final BattleContext context) {
        final int hougBeforeThreshold = daylightHougThreshold(augmentingBeforeThreshold(attackShip, context));
        final double augmentingAfterThreshold = augmentingAfterThreshold(attackShip, context);
        return DoubleMath.roundToInt(hougBeforeThreshold * augmentingAfterThreshold, RoundingMode.DOWN);
    }

    /**
     * 阈值前攻击力 =  [ { 基本攻撃力 × 閾值前補正 + 輕巡適型砲補正 } ]
     * <p>
     * {　}里的部份所計算出來的數值會受到閾值影響，具體閾值為：
     * 晝戰（不計算反潛戰）：150
     * 反潛戰：100
     * 夜戰（非反潛戰）：300
     * <p>
     * 若果{　}里的數值超出了當前閾值，則按以下算式處理：
     * 補正後攻撃力（{　}部份） = 閾值 + √(補正前攻撃力 - 閾值)
     * 括號 [ ] 內的數值需要向下取整
     * <p>
     * 阈值前补正包括：
     * 1.阵型补正
     * 2.航向补正
     * 3.损伤补正
     * 4.反潜相乘补正
     * <p>
     * 这里补正数值属于炮击战
     */
    public double augmentingBeforeThreshold(final IShip attackShip, final BattleContext context) {
        double augmenting = 1d;

        //阵型补正
        final int formationIndex = BattleContextUtils.getMemberFormation(context);
        final double formationAugmenting = FormationSystem.shellingHougAugment(formationIndex);
        augmenting *= formationAugmenting;

        //航向补正
        final int courseIndex = BattleContextUtils.getBattleCourse(context);
        final double courseAugmenting = CourseEnum.shelllingHougAugment(courseIndex);
        augmenting *= courseAugmenting;

        //损伤补正
        if (ShipUtils.isBadlyDmgStatue.test(attackShip)) {
            // TODO 雷击战补正0
            augmenting *= 0.4d;
        } else if (ShipUtils.isMidDmgStatue.test(attackShip)) {
            // TODO 雷击战补正0.8
            augmenting *= 0.7d;
        }

        //反潜套补正
        final List<? extends AbstractSlotItem> slots = attackShip.getSlotItems();
        final boolean hasHydrophone = slots.stream().anyMatch(slot -> SlotItemUtils.getType(slot) == AbstractSlotItem.TYPE_HYDRO_PHONE);
        final boolean hasDepthCharge = slots.stream().anyMatch(slot -> SlotItemUtils.getType(slot) == AbstractSlotItem.TYPE_DEPTH_CHARGE);
        if (hasHydrophone && hasDepthCharge) {
            augmenting *= 1.15d;
        }
        // 阈值前攻击力 = 基本攻击力*阈值前攻击不整参数+轻巡炮攻击力补正
        return augmenting * shellingBaiscHoug(attackShip) + cLGunAugmenting(attackShip);
    }


    /**
     * 阈值后补正 = 徹甲彈特效補正×暴擊補正×彈着觀測射撃補正
     *
     * @return
     */
    public double augmentingAfterThreshold(final UnderSeaShip attackShip, final BattleContext context) {
        double augmenting = 1d;

        final HougekiResult hougekiResult = context.getNowHougekiResult();
        final int attackType = getLast(hougekiResult.getApi_at_type());

        final SlotItemInfo slotInfo = SlotItemInfo.of(attackShip);
        if (slotInfo.getAPAmmoCount() > 0) {
            augmenting *= APAMMO_AUGMENTING;
        }

        //暴擊補正
        final int[] clArray = (int[]) getLast(hougekiResult.getApi_cl_list());
        final boolean isCrtical = clArray[0] == CL_VALUE_CRITICAL;
        if (isCrtical) {
            augmenting *= SHELLING_CRITICAL_AUGMENTING;
        }

        //彈着觀測射撃補正
        switch (attackType) {
            case ATTACK_TYPE_MAIN:
                augmenting *= 1.5d;
                break;
            case ATTACK_TYPE_EXPOSEARMOR:
                augmenting *= 1.3d;
                break;
            case ATTACK_TYPE_DOUBLE:
            case ATTACK_TYPE_RADAR:
                augmenting *= 1.2d;
                break;
            case ATTACK_TYPE_SECONDARY:
                augmenting *= 1.1d;
                break;
            case ATTACK_TYPE_NORMAL:
            default:
        }

        return augmenting;
    }

    @Override
    protected final double getHoumFormationFactor(final BattleContext context) {
        final int memberFormation = BattleContextUtils.getMemberFormation(context);
        final int underSeaFormation = BattleContextUtils.getUnderSeaFormation(context);
        return getHoumFormationFactor(underSeaFormation, memberFormation);
    }


    @Override
    protected int getCurrentSakutekiSum(final BattleContext context) {
        return context.getUnderSeaSakuteki();
    }
}
