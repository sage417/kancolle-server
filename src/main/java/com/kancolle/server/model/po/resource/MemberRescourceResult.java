/**
 * 
 */
package com.kancolle.server.model.po.resource;

/**
 * @author J.K.SAGE
 * @Date 2015年7月12日
 *
 */
public class MemberRescourceResult {

    private int[] api_material;

    /**
     * @param memberResource
     */
    public MemberRescourceResult(Resource memberResource) {
        this.api_material = new int[] { memberResource.getFuel(), memberResource.getBull(), memberResource.getSteel(), memberResource.getBauxite() };
    }

    public int[] getApi_material() {
        return api_material;
    }

    public void setApi_material(int[] api_material) {
        this.api_material = api_material;
    }
}
