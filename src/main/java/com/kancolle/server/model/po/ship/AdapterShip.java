/**
 * 
 */
package com.kancolle.server.model.po.ship;

import java.util.List;

import com.kancolle.server.model.po.slotitem.SlotItem;

/**
 * @author J.K.SAGE
 * @Date 2015年9月5日
 *
 */
public interface AdapterShip {

    List<SlotItem> getAdapterSlotItem();

    int[] getAdapterCurrentEQ();

    int getAdapterTypeId();
    
    int getAdapterLeng();
}
