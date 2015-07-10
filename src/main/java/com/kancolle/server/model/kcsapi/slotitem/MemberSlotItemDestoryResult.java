/**
 * 
 */
package com.kancolle.server.model.kcsapi.slotitem;

/**
 * @author J.K.SAGE
 * @Date 2015年7月10日
 *
 */
public class MemberSlotItemDestoryResult {

    private int[] api_get_material;

    public MemberSlotItemDestoryResult(int[] getMaterials) {
        this.api_get_material = getMaterials;
    }

    public int[] getApi_get_material() {
        return api_get_material;
    }

    public void setApi_get_material(int[] api_get_material) {
        this.api_get_material = api_get_material;
    }
}
