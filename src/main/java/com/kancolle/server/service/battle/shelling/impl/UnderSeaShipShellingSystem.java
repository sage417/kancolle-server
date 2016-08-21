package com.kancolle.server.service.battle.shelling.impl;

import com.google.common.math.DoubleMath;
import com.google.common.math.IntMath;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.battle.SlotItemInfo;
import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.UnderSeaShip;
import com.kancolle.server.model.po.slotitem.AbstractSlotItem;
import com.kancolle.server.service.battle.FormationSystem;
import com.kancolle.server.service.battle.course.CourseEnum;
import com.kancolle.server.utils.logic.battle.BattleContextUtils;
import com.kancolle.server.utils.logic.ship.ShipFilter;
import com.kancolle.server.utils.logic.ship.ShipUtils;
import com.kancolle.server.utils.logic.slot.SlotItemUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.List;

import static com.google.common.collect.Iterables.getLast;
import static com.google.common.collect.Iterables.isEmpty;

@Service
public class UnderSeaShipShellingSystem extends BaseShipShellingSystem<UnderSeaShip, MemberShip> {

    @Autowired
    private BaseShipShellingSystem<MemberShip, UnderSeaShip> memberShipShellingSystem;

    @Override
    public void generateHougkeResult(final UnderSeaShip attackShip, final BattleContext context) {
        prepareContext(context);
        int aerialStatue = context.getUnderSeaAerialState();

        final List<MemberShip> enemySSShips = context.getAliveMemberSSShips();
        final List<MemberShip> enemyOtherShips = context.getAliveMemberNormalShips();
        if (isEmpty(enemySSShips) && isEmpty(enemyOtherShips)) {
            return;
        }
        addToAttackList(attackShip, context);

        final MemberShip defendShip = chooseTargetShip(attackShip, context);

        if (isTaisenAttack(attackShip, enemySSShips)) {
            generateTaiSenAttackList(context, attackShip);
            addToDefendList(defendShip, BaseShipShellingSystem.ATTACK_TYPE_ANTISUBMARINE, context);
            generateTaiSenDamageList(attackShip, defendShip, context);
            if (defendShip.getNowHp() < 0) {
                enemySSShips.remove(defendShip);
            }
        } else {
            int attackType = chooseAttackTypeAndSlotItem(attackShip, defendShip, aerialStatue, context);
            addToDefendList(defendShip, attackType, context);
            generateDamageList(attackShip, defendShip, context);
            if (defendShip.getNowHp() < 0) {
                enemyOtherShips.remove(defendShip);
            }
        }
    }

    @Override
    protected void prepareContext(final BattleContext context) {
        super.prepareContext(context);
        context.setEnemyNormalShips(context.getAliveMemberNormalShips());
        context.setEnemySSShips(context.getAliveMemberSSShips());
    }

    @Override
    protected void augmentingDamage(final UnderSeaShip attackShip, final MemberShip defendShip, final int[] damages, final BattleContext context) {

    }

    @Override
    protected void callbackAfterDamage(final UnderSeaShip attackShip, final MemberShip defendShip, final int[] damages, final BattleContext context) {
        if (!ShipFilter.isAlive.test(defendShip)) {

        }
    }

    @Override
    protected int[] generateOnceDamageResult(final UnderSeaShip attackShip, final MemberShip defendShip, final BattleContext context) {
        return new int[0];
    }

    @Override
    protected int[] generateTwiceDamageResult(final UnderSeaShip attackShip, final MemberShip defendShip, final BattleContext context) {
        return new int[0];
    }

    private void generateTaiSenDamageList(final UnderSeaShip attackShip, final MemberShip defendShip, final BattleContext context) {
    }

    private boolean isTaisenAttack(final IShip attackShip, final List<? extends IShip> enemySSShips) {
        return !enemySSShips.isEmpty() && ShipFilter.antiSSShipFilter.test(attackShip);
    }

    /**
     * 影响舰船命中率包含：
     * 1.舰船等级
     * 2.装备
     * 3.幸运值
     * <p>
     * 不考虑阵型加成
     *
     * @param ship
     * @return
     */
    private double shipHitRatios(final UnderSeaShip ship) {
        // TODO UnderSeaShip lv
        int nowLv = 1;
        final double levelRatios = IntMath.sqrt(--nowLv, RoundingMode.DOWN) * HIT_LEVEL_AUGMENTING;

        final int lucky = ship.getNowLuck();
        final double luckyRatios = lucky * HIT_LUCK_AUGMENTING;

        // TODO cacheValue
        final int slotHoum = ship.getSlotItems().stream().mapToInt(AbstractSlotItem::getHoum).sum();
        final double slotRatios = slotHoum * HIT_SLOT_AUGMENTING;

        return HIT_BASE_RADIOS + levelRatios + luckyRatios + slotRatios;
    }

    @Override
    protected double combineKaihiRatio(final UnderSeaShip ship, final BattleContext context) {

        final int shipKaihi = ship.getShipKaihi();

        final int courseIdx = BattleContextUtils.getBattleCourse(context);
        final double courseAugmenting = CourseEnum.shelllingHougAugment(courseIdx);

        return houkThreshold(shipKaihi) * courseAugmenting;
    }

    public void generateDamageList(final UnderSeaShip attackShip, final MemberShip defendShip, final BattleContext context) {
        final HougekiResult hougekiResult = context.getNowHougekiResult();
        final int attackType = getLast(hougekiResult.getApi_at_type());
        int damageSum = 0;

        final int[] clArray;

        switch (attackType) {
            case ATTACK_TYPE_NORMAL:
            case ATTACK_TYPE_EXPOSEARMOR:
            case ATTACK_TYPE_MAIN:
            case ATTACK_TYPE_RADAR:
            case ATTACK_TYPE_SECONDARY:
                final boolean hit = isHit(shipHitRatios(attackShip), memberShipShellingSystem.combineKaihiRatio(defendShip, context));
                if (!hit)
                    clArray = CL_SINGLE_MISS;
                else
                    clArray = RandomUtils.nextInt(0, 9) > 1 ? CL_SINGLE_HIT : CL_SINGLE_CRTICAL;
                hougekiResult.getApi_cl_list().add(clArray);

                final int hougAfterThreshold = attackValue(attackShip, defendShip, context);
                final int damageValue = damageValue(hougAfterThreshold, defendShip, false);
                hougekiResult.getApi_damage().add(new int[]{damageValue});
                damageSum += damageValue;
                break;
            case ATTACK_TYPE_DOUBLE:
                break;
            default:
                throw new IllegalArgumentException("attack type error");
        }
        final int nowHp = defendShip.getNowHp();
        defendShip.setNowHp(nowHp - damageSum);
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
        final double formationAugmenting = FormationSystem.shelllingHougAugment(formationIndex);
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
        final boolean isCrtical = clArray[0] == CL_VALUE_CRTICAL;
        if (isCrtical) {
            augmenting *= SHELLING_CRTICAL_AUGMENTING;
        }

        //TODO 熟練艦載機暴擊額外補正
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
}
