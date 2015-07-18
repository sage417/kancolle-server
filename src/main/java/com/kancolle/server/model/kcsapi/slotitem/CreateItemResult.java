/**
 * 
 */
package com.kancolle.server.model.kcsapi.slotitem;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.model.po.resource.Resource;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;

/**
 * @author J.K.SAGE
 * @Date 2015年7月12日
 *
 */
public class CreateItemResult {

    public static final int CREATE_SUCCESS = 1;

    public static final int CREATE_FAIL = 0;

    @JSONField(ordinal = 1)
    private int api_create_flag;

    @JSONField(ordinal = 2)
    private int api_shizai_flag;

    @JSONField(ordinal = 3)
    private CreateItem api_slot_item;

    @JSONField(ordinal = 4)
    private String api_fdata;

    @JSONField(ordinal = 5)
    private int[] api_material;

    @JSONField(ordinal = 6)
    private int api_type3;

    @JSONField(ordinal = 7)
    private long[] api_unsetslot;

    public CreateItemResult(int api_create_flag, int api_shizai_flag, String api_fdata, Resource memberResource) {
        this(memberResource);
        this.api_create_flag = api_create_flag;
        this.api_shizai_flag = api_shizai_flag;
        this.api_fdata = api_fdata;
    }

    public CreateItemResult(int api_create_flag, int api_shizai_flag, MemberSlotItem memberSlotItem, Resource memberResource, int api_type3, long[] api_unsetslot) {
        this(memberResource);
        this.api_create_flag = api_create_flag;
        this.api_shizai_flag = api_shizai_flag;
        this.api_slot_item = new CreateItem(memberSlotItem.getMemberSlotItemId(), memberSlotItem.returnSlotItemId());
        this.api_type3 = api_type3;
        this.api_unsetslot = api_unsetslot;
    }

    private CreateItemResult(Resource memberResource) {
        super();
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

    public CreateItem getApi_slot_item() {
        return api_slot_item;
    }

    public void setApi_slot_item(CreateItem api_slot_item) {
        this.api_slot_item = api_slot_item;
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

    public int getApi_type3() {
        return api_type3;
    }

    public void setApi_type3(int api_type3) {
        this.api_type3 = api_type3;
    }

    public long[] getApi_unsetslot() {
        return api_unsetslot;
    }

    public void setApi_unsetslot(long[] api_unsetslot) {
        this.api_unsetslot = api_unsetslot;
    }
}
