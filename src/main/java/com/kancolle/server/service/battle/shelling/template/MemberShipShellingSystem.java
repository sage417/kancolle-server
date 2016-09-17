package com.kancolle.server.service.battle.shelling.template;

import com.google.common.math.DoubleMath;
import com.google.common.math.IntMath;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.battle.SlotItemInfo;
import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.UnderSeaShip;
import com.kancolle.server.service.battle.shelling.apply.BattleContextApply;
import com.kancolle.server.utils.logic.battle.BattleContextUtils;
import com.kancolle.server.utils.logic.common.LvUtils;
import com.kancolle.server.utils.logic.ship.ShipFilter;
import com.kancolle.server.utils.logic.ship.ShipUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Iterables.getLast;

@Service
public class MemberShipShellingSystem extends BaseShipShellingSystem<MemberShip, UnderSeaShip> {

    @Autowired
    @Qualifier("memberBattleContextApply")
    private BattleContextApply apply;

    @Override
    protected void prepareContext(final BattleContext context) {
        context.setApply(apply);
        context.setEnemyNormalShips(context.getAliveUnderSeaNormalShips());
        context.setEnemySSShips(context.getAliveUnderSeaSSShips());
    }

    @Override
    protected int[] generateActualDamage(UnderSeaShip defendShip, int[] damages, BattleContext context) {
        return damages;
    }

    @Override
    protected void callbackAfterDamage(final MemberShip attackShip, final UnderSeaShip defendShip, final int[] actualDamages, final int[] damages, final BattleContext context) {
        super.callbackAfterDamage(attackShip, defendShip, actualDamages, damages, context);

        int damageSum = Arrays.stream(damages).sum();

        final Map<MemberShip, Integer> damageSumMap = context.getDamageSum();
        Integer memberShipDamageSum = damageSumMap.getOrDefault(attackShip, 0);
        damageSumMap.put(attackShip, memberShipDamageSum + damageSum);
    }

    private boolean isTaisenAttack(final IShip attackShip, final List<? extends IShip> enemySSShips) {
        return !enemySSShips.isEmpty() && ShipFilter.antiSSShipFilter.test(attackShip);
    }

    @Override
    protected final double shipHoumRatios(final MemberShip attackShip, final BattleContext context) {
        final int shipCond = attackShip.getCond();
        return HIT_BASE_RADIOS + (HIT_MEMBER_RADIOS + shipHoumRatios(attackShip)) * getHoumFormationFactor(context) * getHoumCondFactor(shipCond);
    }

    @Override
    protected double shipKaihiRatio(final UnderSeaShip ship, final BattleContext context) {

        final int formation = BattleContextUtils.getUnderSeaFormation(context);

        return houkThreshold(ship) * getKaihiFormationFactor(formation);
    }

    /**
     * 阵型对命中值的因子
     *
     * @param context
     * @return
     */
    @Override
    protected final double getHoumFormationFactor(final BattleContext context) {
        final int memberFormation = BattleContextUtils.getMemberFormation(context);
        final int enemyFormation = BattleContextUtils.getUnderSeaFormation(context);
        return getHoumFormationFactor(memberFormation, enemyFormation);
    }

    /**
     * 状态值对命中值的因子
     *
     * @param cond
     * @return
     */
    private double getHoumCondFactor(final int cond) {
        if (cond < MemberShip.BAD_COND) {
            return 0.5d;
        } else if (cond >= MemberShip.GOOD_COND) {
            return 1.2d;
        } else {
            return 1d;
        }
    }

    @Override
    protected final int[] generateDamageResult(final MemberShip attackShip, final UnderSeaShip defendShip, final int attackType, final int[] criticals, final BattleContext context) {
        int[] damageList = new int[0];

        switch (attackType) {
            case ATTACK_TYPE_NORMAL:
            case ATTACK_TYPE_EXPOSEARMOR:
            case ATTACK_TYPE_MAIN:
            case ATTACK_TYPE_RADAR:
            case ATTACK_TYPE_SECONDARY:
                final int hougAfterThreshold = attackValue(attackShip, defendShip, context);
                final int damageValue = damageValue(hougAfterThreshold, defendShip);
                damageList = new int[]{damageValue};
                break;
            case ATTACK_TYPE_DOUBLE:
                break;
            default:
                throw new IllegalArgumentException("attack type error");
        }
        return damageList;
    }

    private void generateTaiSenDamageList(final MemberShip attackShip, final UnderSeaShip defendShip, final BattleContext context) {
        final HougekiResult hougekiResult = context.getNowHougekiResult();
        int damageSum = 0;
        final int[] clArray;

        final boolean hit = isHit(shipHoumRatios(attackShip, context), shipKaihiRatio(defendShip, context));
        if (!hit) {
            clArray = CL_SINGLE_MISS;
        } else {
            clArray = RandomUtils.nextInt(0, 9) > 1 ? CL_SINGLE_HIT : CL_SINGLE_CRTICAL;
        }
        hougekiResult.getApi_cl_list().add(clArray);

        final int hougAfterThreshold = taiSenValue(attackShip, defendShip, context);
        final int damageValue = damageValue(hougAfterThreshold, defendShip);
        hougekiResult.getApi_damage().add(new int[]{damageValue});
        damageSum += damageValue;

        final Map<MemberShip, Integer> damageSumMap = context.getDamageSum();

        Integer memberShipDamageSum = damageSumMap.getOrDefault(attackShip, 0);
        memberShipDamageSum += damageSum;
        damageSumMap.put(attackShip, memberShipDamageSum);

        int nowHp = defendShip.getNowHp() - damageSum;
        if (nowHp < 0) {
            nowHp = 0;
        }
        defendShip.setNowHp(nowHp);

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
        //TODO 修改装备攻击力补正
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
    private int taiSenBasicHoug(final IShip ship) {
        //TODO 联合舰队基本攻击力补正
        //TODO 修改装备攻击路补正
        final int shipTaisen = LvUtils.getLvValue(ship.getShip().getTaisen(), ship.getNowLv());
        final int slotTaisen = ship.getShipTaiSen() - shipTaisen;
        final int augmenting = ShipFilter.carrierFilter.test(ship) ? AIRCRAFT_AUGMENTING : DEPTH_CHARGE_AUGMENTING;
        return 2 * IntMath.sqrt(shipTaisen, RoundingMode.CEILING) + DoubleMath.roundToInt(1.5d * slotTaisen, RoundingMode.CEILING) + augmenting;
    }

    private int attackValue(final MemberShip attackShip, final UnderSeaShip defendShip, final BattleContext context) {
        final int hougBeforeThreshold = daylightHougThreshold(augmentingBeforeThreshold(attackShip, context));
        final double augmentingAfterThreshold = augmentingAfterThreshold(attackShip, context);
        return DoubleMath.roundToInt(hougBeforeThreshold * augmentingAfterThreshold, RoundingMode.DOWN);
    }

    private int taiSenValue(final MemberShip attackShip, final UnderSeaShip defendShip, final BattleContext context) {
        final int hougBeforeThreshold = taiSenHougThreshold(taiSenAugmentingBeforeThreshold(attackShip, context));
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
        final int basicHoug = shellingBaiscHoug(attackShip);
        double augmenting = basicAugmentBeforeThreshold(attackShip, context);

        if (augmenting < 0d) {
            augmenting = 0d;
        }

        // 阈值前攻击力 = 基本攻击力 * 阈值前攻击补正参数 + 轻巡炮攻击力补正
        return augmenting * basicHoug + cLGunAugmenting(attackShip);
    }

    public double taiSenAugmentingBeforeThreshold(final IShip attackShip, final BattleContext context) {
        final int taisenBasicHoug = taiSenBasicHoug(attackShip);
        double augmenting = basicAugmentBeforeThreshold(attackShip, context);

        //反潜套补正
        final double taisenAugmenting = taisenShellingAugmenting(attackShip);
        augmenting *= taisenAugmenting;

        if (augmenting < 0d) {
            augmenting = 0d;
        }

        return augmenting * taisenBasicHoug;
    }

    /**
     * 阈值后补正 = 徹甲彈特效補正×暴擊補正×彈着觀測射撃補正
     *
     * @return
     */
    public double augmentingAfterThreshold(final MemberShip attackShip, final BattleContext context) {
        double augmenting = 1d;

        final HougekiResult hougekiResult = context.getNowHougekiResult();
        final int attackType = BattleContextUtils.getCurrentAttackType(context);

        final SlotItemInfo slotInfo = SlotItemInfo.of(attackShip);
        if (slotInfo.getAPAmmoCount() > 0) {
            augmenting *= APAMMO_AUGMENTING;
        }

        //暴擊補正
        final int[] clArray = (int[]) getLast(hougekiResult.getApi_cl_list());
        // TODO double attack
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
    protected int getCurrentSakutekiSum(final BattleContext context) {
        return context.getMemberSakuteki();
    }
}
