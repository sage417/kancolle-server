/**
 * 
 */
package com.kancolle.server.model.po.slotitem;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author J.K.SAGE
 * @Date 2015年10月2日
 *
 */
@Alias("UnderSeaSlotItem")
public class UnderSeaSlotItem extends AbstractSlotItem implements ISlotItem, Serializable {

    private static final long serialVersionUID = -1044011355581146491L;

    @Override
    public int getTaiSen() {
        return getTais();
    }
}
