/**
 * 
 */
package com.kancolle.server.model.kcsapi.slotitem;

import com.kancolle.server.model.po.resource.Resource;

/**
 * @author J.K.SAGE
 * @Date 2015年7月12日
 *
 */
public class CreateItemResult {

    private int api_create_flag;

    private int api_shizai_flag;

    private String api_fdata;

    private int[] api_material;

    public CreateItemResult(Resource memberResource) {
        this.api_material = new int[] { memberResource.getFuel(), memberResource.getBull(), memberResource.getSteel(), memberResource.getBauxite(), memberResource.getFastBuild(),
                memberResource.getFastRecovery(), memberResource.getDevItem(), memberResource.getDevItem() };
    }

    public int getApi_create_flag() {
        return api_create_flag;
    }

    public void setApi_create_flag(int api_create_flag) {
        this.api_create_flag = api_create_flag;
    }

    public int getApi_shizai_flag() {
        return api_shizai_flag;
    }

    public void setApi_shizai_flag(int api_shizai_flag) {
        this.api_shizai_flag = api_shizai_flag;
    }

    public String getApi_fdata() {
        return api_fdata;
    }

    public void setApi_fdata(String api_fdata) {
        this.api_fdata = api_fdata;
    }

    public int[] getApi_material() {
        return api_material;
    }

    public void setApi_material(int[] api_material) {
        this.api_material = api_material;
    }
}
