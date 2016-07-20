/**
 * 
 */
package com.kancolle.server.model.po.slotitem;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**
 * @author J.K.SAGE
 * @Date 2015年10月2日
 *
 */
@Alias("EnemySlotItem")
public class EnemySlotItem extends AbstractSlotItem implements ISlotItem, Serializable {

    private static final long serialVersionUID = -1044011355581146491L;

    @Override
    public int getTaiSen() {
        return getTais();
    }
}
