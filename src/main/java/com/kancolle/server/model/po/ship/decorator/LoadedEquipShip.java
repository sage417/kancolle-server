package com.kancolle.server.model.po.ship.decorator;

import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.model.po.slotitem.ISlotItem;

import java.util.List;

/**
 * Created by J.K.SAGE on 2017/1/14.
 */
public class LoadedEquipShip implements IShip {

    private final IShip originShip;


    public LoadedEquipShip(IShip originShip) {
        this.originShip = originShip;


    }

    public void loadEquip(){
        //List<ISlotItem> slotItems = this.originShip.getSlotItems();

    }

    @Override
    public Ship getShip() {
        return originShip.getShip();
    }

    @Override
    public List<? extends ISlotItem> getSlotItems() {
        return originShip.getSlotItems();
    }

    @Override
    public int[] getCurrentEQ() {
        return originShip.getCurrentEQ();
    }

    @Override
    public int getLeng() {
        return 0;
    }

    @Override
    public int getNowLuck() {
        return 0;
    }

    @Override
    public int getNowLv() {
        return originShip.getNowLv();
    }

    @Override
    public int getNowHp() {
        return originShip.getNowHp();
    }

    @Override
    public void setNowHp(int nowHp) {
        originShip.setNowHp(nowHp);
    }

    @Override
    public int getMaxHp() {
        return 0;
    }

    @Override
    public int getShipKaryoku() {
        return 0;
    }

    @Override
    public int getShipRaisou() {
        return 0;
    }

    @Override
    public int getShipTaiku() {
        return 0;
    }

    @Override
    public int getShipSoukou() {
        return 0;
    }

    @Override
    public int getShipTaiSen() {
        return 0;
    }

    @Override
    public int getShipKaihi() {
        return 0;
    }

    @Override
    public int getShipSakuteki() {
        return 0;
    }
}
