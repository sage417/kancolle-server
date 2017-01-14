package com.kancolle.server.model.po.ship;

import com.kancolle.server.model.po.slotitem.ISlotItem;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年10月2日
 *
 */
public interface IShip {

    Ship getShip();

    List<? extends ISlotItem> getSlotItems();

    int[] getCurrentEQ();

    int getLeng();

    int getNowLuck();

    int getNowLv();

    int getNowHp();

    void setNowHp(int nowHp);

    int getMaxHp();

    /** 火力 */
    int getShipKaryoku();

    /** 雷装 */
    int getShipRaisou();

    /** 对空 */
    int getShipTaiku();

    /** 装甲 */
    int getShipSoukou();

    /** 对潜 */
    int getShipTaiSen();

    /** 回避 */
    int getShipKaihi();

    /** 索敌 */
    int getShipSakuteki();
}
