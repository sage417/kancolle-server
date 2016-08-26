package com.kancolle.server.model.kcsapi.duty;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.pagehelper.Page;
import com.kancolle.server.model.po.duty.MemberDuty;

import java.util.List;

@JsonPropertyOrder(value = {
        "api_count", "api_page_count", "api_disp_page", "api_list",
        "api_exec_count", "api_exec_type"
})
public class MemberDutyPageList {

    @JsonProperty(value = "api_count")
    @JSONField(ordinal = 1)
    private int api_count;

    @JsonProperty(value = "api_page_count")
    @JSONField(ordinal = 2)
    private int api_page_count;

    @JsonProperty(value = "api_disp_page")
    @JSONField(ordinal = 3)
    private int api_disp_page;

    @JsonProperty(value = "api_list")
    @JSONField(ordinal = 4)
    private List<MemberDuty> api_list;

    @JsonProperty(value = "api_exec_count")
    @JSONField(ordinal = 5)
    private int api_exec_count;

    @JsonProperty(value = "api_exec_type")
    @JSONField(ordinal = 6)
    private int api_exec_type = 3120796;

    public MemberDutyPageList(Page<MemberDuty> pageList, int parallelQuestCount) {
        this.api_count = (int) pageList.getTotal();
        this.api_page_count = pageList.getPages();
        this.api_disp_page = pageList.getPageNum();
        this.api_list = pageList.getResult();
        this.api_exec_count = parallelQuestCount;
    }

    public int getApi_count() {
        return api_count;
    }

    public void setApi_count(int api_count) {
        this.api_count = api_count;
    }

    public int getApi_page_count() {
        return api_page_count;
    }

    public void setApi_page_count(int api_page_count) {
        this.api_page_count = api_page_count;
    }

    public int getApi_disp_page() {
        return api_disp_page;
    }

    public void setApi_disp_page(int api_disp_page) {
        this.api_disp_page = api_disp_page;
    }

    public List<MemberDuty> getApi_list() {
        return api_list;
    }

    public void setApi_list(List<MemberDuty> api_list) {
        this.api_list = api_list;
    }

    public int getApi_exec_count() {
        return api_exec_count;
    }

    public void setApi_exec_count(int api_exec_count) {
        this.api_exec_count = api_exec_count;
    }

    public int getApi_exec_type() {
        return api_exec_type;
    }

    public void setApi_exec_type(int api_exec_type) {
        this.api_exec_type = api_exec_type;
    }
}
