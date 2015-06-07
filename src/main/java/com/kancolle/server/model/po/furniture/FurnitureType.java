/**
 * 
 */
package com.kancolle.server.model.po.furniture;

/**
 * @author J.K.SAGE
 * @Date 2015年6月7日
 *
 */
public enum FurnitureType {

    FLOOR(0), WALLPAPER(1), WINDOW(2), WALLHANGING(3), SHELF(4), DESK(5), ;

    private FurnitureType(int typeId) {
        this.typeId = typeId;
    }

    private int typeId;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
