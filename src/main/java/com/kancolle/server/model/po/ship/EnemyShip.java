package com.kancolle.server.model.po.ship;

import com.kancolle.server.model.po.slotitem.AbstractSlotItem;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by J.K.SAGE on 2016/3/29 0029.
 */
@Alias("EnemyShip")
public class EnemyShip extends AbstractShip implements Serializable {

    private int nowHp = getShip().getTaik().getMaxValue();

    private int[] currentEQ = Arrays.copyOf(getShip().getMaxEq(), getShip().getMaxEq().length);

    private EnemyShipTemplate shipTemplate;

    @Override
    public int[] getCurrentEQ() {
        return this.currentEQ;
    }

    @Override
    public int getNowHp() {
        return this.nowHp;
    }

    public void setCurrentEQ(int[] currentEQ) {
        this.currentEQ = currentEQ;
    }

    public void setNowHp(int nowHp) {
        this.nowHp = nowHp;
    }

    public EnemyShipTemplate getShipTemplate() {
        return shipTemplate;
    }

    public void setShipTemplate(EnemyShipTemplate shipTemplate) {
        this.shipTemplate = shipTemplate;
    }

    //------抽象方法------//

    @Override
    public List<? extends AbstractSlotItem> getSlotItems() {
        return getShipTemplate().getSlot();
    }

    @Override
    public int getLeng() {
        return getShip().getLeng();
    }

    @Override
    public int getShipSoukou() {
        return getShip().getSouk().getMinValue();
    }

    @Override
    public int getNowLuck() {
        return getShip().getLuck().getMinValue();
    }

    @Override
    public int getMaxHp() {
        return getShip().getTaik().getMaxValue();
    }

    @Override
    public int getShipTaiSen() {
        return getShip().getTaisen().getMinValue();
    }

    @Override
    public int getShipKaihi() {
        return getShip().getKaihi().getMinValue();
    }

    @Override
    public int getShipKaryoku() {
        return getShip().getHoug().getMinValue();
    }

    @Override
    public int getShipRaisou() {
        return getShip().getRaig().getMinValue();
    }

    @Override
    public int getShipSakuteki() {
        return getShip().getSakuteki().getMinValue();
    }

    //------抽象方法------//
}
