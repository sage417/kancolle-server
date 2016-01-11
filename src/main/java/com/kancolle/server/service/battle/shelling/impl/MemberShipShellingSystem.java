package com.kancolle.server.service.battle.shelling.impl;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.math.IntMath;
import com.google.common.primitives.Longs;
import com.kancolle.server.model.kcsapi.battle.houku.KouKuResult;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.battle.SlotItemInfo;
import com.kancolle.server.model.po.ship.AbstractShip;
import com.kancolle.server.model.po.ship.EnemyShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.slotitem.AbstractSlotItem;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.service.battle.FormationSystem;
import com.kancolle.server.service.battle.aerial.AerialUtils;
import com.kancolle.server.service.battle.course.CourseEnum;
import com.kancolle.server.utils.CollectionsUtils;
import com.kancolle.server.utils.logic.ship.ShipFilter;
import com.kancolle.server.utils.logic.ship.ShipUtils;
import com.kancolle.server.utils.logic.slot.SlotItemUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.google.common.collect.Iterables.getLast;

@Service
public class MemberShipShellingSystem extends AbstractShipShellingSystem<MemberShip, EnemyShip> {

    @Override
    public void generateHougkeResult(MemberShip attackShip, BattleContext context) {
        generateAttackList(attackShip, context);
        List<EnemyShip> enemySSShips = context.getEnemySSShips();
        List<EnemyShip> enemyOtherShips = context.getEnemyOtherShips();

        if (isTaisenAttack(attackShip, enemySSShips)) {
            generateShellingAttackTypeList(attackShip, context);
            EnemyShip defShip = generateDefendList(enemySSShips, context);
        } else {
            generateShellingAttackTypeList(attackShip, context);
            EnemyShip defendShip = generateDefendList(enemyOtherShips, context);
            //generateSlotItemList(attackShip, context);
            //generateCrticalList(attackShip, defendShip, context);
            generateDamageList(attackShip, defendShip, context);
        }
    }

    private boolean isTaisenAttack(AbstractShip attackShip, List<? extends AbstractShip> enemySSShips) {
        return !enemySSShips.isEmpty() && ShipFilter.antiSSShipFilter.test(attackShip);
    }

    @Override
    public void generateAttackList(MemberShip attackShip, BattleContext context) {
        HougekiResult hougekiResult = context.getNowHougekiResult();
        ImmutableBiMap<Integer, AbstractShip> shipsMap = context.getShipMap();
        hougekiResult.getApi_at_list().add(shipsMap.inverse().get(attackShip).intValue());
    }

    @Override
    public EnemyShip generateDefendList(List<EnemyShip> enemySSShips, BattleContext context) {
        EnemyShip defendShip = CollectionsUtils.randomGet(enemySSShips);
        HougekiResult hougekiResult = context.getNowHougekiResult();

        int attackType = hougekiResult.getApi_at_type().getLast().intValue();
        ImmutableBiMap<Integer, AbstractShip> shipsMap = context.getShipMap();

        int defShipIdx = shipsMap.inverse().get(defendShip).intValue();

        int[] defArr = attackType == ATTACK_TYPE_DOUBLE ? new int[]{defShipIdx, defShipIdx} : new int[]{defShipIdx};
        hougekiResult.getApi_df_list().add(defArr);
        return defendShip;
    }

    @Override
    protected int hitRatios(MemberShip ship) {
        int nowLv = ship.getLv();
        int lucky = ship.getNowLuck();
        int slotHoum = ship.getSlot().stream().mapToInt(MemberSlotItem::getHoum).sum();
        int hitValue = 95 + 2 * IntMath.sqrt(nowLv - 1, RoundingMode.DOWN) + slotHoum + 3 * lucky / 20;
        int cond = ship.getCond();
        if (cond < 30) {
            return hitValue / 2;
        } else if (cond < 40) {
            return hitValue * 3 / 4;
        }
        return hitValue;
    }

    @Override
    public void generateAttackTypeList(MemberShip attackShip, BattleContext context) {
        List<EnemyShip> enemySSShips = context.getEnemySSShips();
        if (isTaisenAttack(attackShip, enemySSShips)) {
            generateTaiSenAttackList(context);
        } else {
            generateShellingAttackTypeList(attackShip, context);
        }
    }

    /**
     * 彈著觀測射擊：
     * <p>
     * 發動條件：
     * 1.必須有觸發航空戰，且我方取得制空優勢或制空權確保下才會有機會發動
     * 2.艦娘須裝備上水偵或水爆，且水偵或水爆數量必須大於1才有機會發動
     * 3.大破艦娘不能發動彈著觀測射擊
     * 4.滿足上述發動配置的裝備數量皆可發動，當裝備滿足複數類型的特殊攻擊時，會機率性的發動其中一樣
     * 若彈著觀測射擊未發動成功，則會進行通常砲擊
     */
    private void generateShellingAttackTypeList(MemberShip attackShip, BattleContext context) {
        LinkedList<Integer> at_type_list = context.getNowHougekiResult().getApi_at_type();
        LinkedList<Object> si_list = context.getNowHougekiResult().getApi_si_list();

        KouKuResult kouKuResult = context.getBattleResult().getApi_kouku();
        SlotItemInfo info = SlotItemInfo.of(attackShip);

        if (!AerialUtils.testAerialAdvence(kouKuResult) || ShipUtils.isBadlyDmg.test(attackShip) || info.getSearchPlaneCount() == 0) {
            generateNormalShellingAttackTypeAndSlotItemList(info, context);
            return;
        }

        int mainGunCount = info.getMainGunCount();
        int secondaryGunCount = info.getSecondaryGunCount();

        // 连击(主主)
        if (mainGunCount > 1) {
            at_type_list.add(ATTACK_TYPE_DOUBLE);
            si_list.add(Longs.asList(info.getMainGunId()).subList(0, 2));
            return;
        }

        // 主副CI(主副)
        if (mainGunCount > 0 && secondaryGunCount > 0) {
            at_type_list.add(ATTACK_TYPE_SECONDARY);
            si_list.add(Longs.asList(info.getMainGunId()[0], info.getSecondaryGunId()[0]));
            return;
        }

        // 电探CI(主副+电探)
        if (mainGunCount == 1 && secondaryGunCount == 1 && info.getRadarCount() == 1) {
            at_type_list.add(ATTACK_TYPE_RADAR);
            si_list.add(Longs.asList(info.getMainGunId()[0], info.getSecondaryGunId()[0], info.getRadarId()[0]));
            return;
        }

        // 撤甲弹CI(主副+撤甲)
        if (mainGunCount == 1 && secondaryGunCount == 1 && info.getAPAmmoCount() == 1) {
            at_type_list.add(ATTACK_TYPE_EXPOSEARMOR);
            si_list.add(Longs.asList(info.getMainGunId()[0], info.getSecondaryGunId()[0], info.getApAmmoId()[0]));
            return;
        }

        // 主炮CI(主主+撤甲)
        if (mainGunCount == 2 && info.getAPAmmoCount() == 1) {
            at_type_list.add(ATTACK_TYPE_MAIN);
            si_list.add(Longs.asList(info.getMainGunId()[0], info.getMainGunId()[1], info.getApAmmoId()[0]));
            return;
        }

        generateNormalShellingAttackTypeAndSlotItemList(info, context);
    }

    private void generateNormalShellingAttackTypeAndSlotItemList(SlotItemInfo info, BattleContext context) {
        LinkedList<Integer> at_type_list = context.getNowHougekiResult().getApi_at_type();
        LinkedList<Object> si_list = context.getNowHougekiResult().getApi_si_list();

        at_type_list.add(ATTACK_TYPE_NORMAL);
        if (info.getMainGunCount() > 0) {
            si_list.add(info.getMainGunId()[0]);
            return;
        }
        if (info.getSecondaryGunCount() > 0) {
            si_list.add(info.getSecondaryGunId()[0]);
            return;
        }
        si_list.add(Collections.singleton(-1));
    }

    @Override
    public void generateSlotItemList(MemberShip ship, BattleContext context) {
    }

    @Override
    public void generateCrticalList(MemberShip attackShip, EnemyShip defShip, BattleContext context) {
        HougekiResult hougekiResult = context.getNowHougekiResult();
        int attackType = getLast(hougekiResult.getApi_at_type()).intValue();

        int[] clArray = null;

        switch (attackType) {
            case ATTACK_TYPE_NORMAL:
                break;
            case ATTACK_TYPE_DOUBLE:
                break;
            case ATTACK_TYPE_EXPOSEARMOR:
                break;
            case ATTACK_TYPE_MAIN:
                break;
            case ATTACK_TYPE_RADAR:
                break;
            case ATTACK_TYPE_SECONDARY:
                break;
            default:
                throw new IllegalArgumentException("attack type error");
        }
        hougekiResult.getApi_cl_list().add(clArray);
    }

    @Override
    public void generateDamageList(MemberShip attackShip, EnemyShip defendShip, BattleContext context) {
        HougekiResult hougekiResult = context.getNowHougekiResult();
        int attackType = getLast(hougekiResult.getApi_at_type()).intValue();

        int[] clArray;

        switch (attackType) {
            case ATTACK_TYPE_NORMAL:
                boolean hit = isHit(hitRatios(attackShip), enemyShipShellingKaihi(defendShip));
                if (!hit)
                    clArray = CL_SINGLE_MISS;
                else
                    clArray = RandomUtils.nextInt(0, 9) > 1 ? CL_SINGLE_HIT : CL_SINGLE_CRTICAL;
                hougekiResult.getApi_cl_list().add(clArray);

                int attackValue = daylightHougThreshold(augmentingBeforeThreshold(attackShip, context));
                int damageValue = damageValue(attackValue, defendShip, false);
                context.getNowHougekiResult().getApi_damage().add(new int[]{damageValue});
                break;
            case ATTACK_TYPE_DOUBLE:
                break;
            case ATTACK_TYPE_EXPOSEARMOR:
                boolean ci_hit = isCIHit(hitRatios(attackShip), enemyShipShellingKaihi(defendShip));
                break;
            case ATTACK_TYPE_MAIN:
                break;
            case ATTACK_TYPE_RADAR:
                break;
            case ATTACK_TYPE_SECONDARY:
                break;
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
    private int shellingBaiscHoug(AbstractShip ship) {
        //TODO 联合舰队基本攻击力补正
        //TODO 修改装备攻击力补正
        int shipHoug = ship.getShipKaryoku();

        if (ShipFilter.carrierFilter.test(ship)) {
            int raiSou = ship.getShipRaisou();
            int baKu = ShipUtils.getBakuValue(ship);
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
    private int antiSubmarineBasicHoug(MemberShip ship) {
        //TODO 联合舰队基本攻击力补正
        //TODO 修改装备攻击路补正
        return 0;
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
    public double augmentingBeforeThreshold(AbstractShip attackShip, BattleContext context) {
        double augmenting = 1d;
        int[] formationArray = context.getBattleResult().getApi_formation();

        //阵型补正
        int formationIndex = formationArray[0];
        double formationAugmenting = FormationSystem.shellingAugmenting(formationIndex);
        augmenting *= formationAugmenting;

        //航向补正
        int courseIndex = formationArray[2];
        double courseAugmenting = CourseEnum.values()[--courseIndex].getAugmenting();
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
        List<? extends AbstractSlotItem> slots = attackShip.getSlotItems();
        boolean hasHydrophone = slots.stream().anyMatch(slot -> SlotItemUtils.getType(slot) == AbstractSlotItem.TYPE_HYDROPHONE);
        boolean hasDepthCharge = slots.stream().anyMatch(slot -> SlotItemUtils.getType(slot) == AbstractSlotItem.TYPE_DEPTHCHARGE);
        if (hasHydrophone && hasDepthCharge) {
            augmenting *= 1.15d;
        }
        // 阈值前攻击力 = 基本攻击力*阈值前攻击不整参数+轻巡炮攻击力补正
        return augmenting * shellingBaiscHoug(attackShip) + cLGunAugmenting();
    }


    /**
     * 阈值后补正 = 徹甲彈特效補正×暴擊補正×彈着觀測射撃補正
     *
     * @return
     */
    public double augmentingAfterThreshold(MemberShip attackShip, BattleContext context) {
        double augmenting = 1d;

        HougekiResult hougekiResult = context.getNowHougekiResult();
        int attackType = getLast(hougekiResult.getApi_at_type()).intValue();


        //徹甲彈特效補正
        SlotItemInfo info = SlotItemInfo.of(attackShip);

        //暴擊補正
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
}
