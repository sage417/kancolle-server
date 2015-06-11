/**
 * 
 */
package com.kancolle.server.model.kcsapi.useitem.item;

/**
 * @author J.K.SAGE
 * @Date 2015年6月11日
 *
 */
public class FurnitureCoin extends GetItem {

    public FurnitureCoin(int count) {
        setApi_usemst(5);
        setApi_mst_id(44);
        setApi_getcount(count);
    }
}
