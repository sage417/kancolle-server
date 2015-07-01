/**
 * 
 */
package com.kancolle.server.model.kcsapi.deck;

/**
 * @author J.K.SAGE
 * @Date 2015年7月1日
 *
 */
public class MemberDeckPortChangeResult {

    private int api_change_count;

    public MemberDeckPortChangeResult(int api_change_count) {
        super();
        this.api_change_count = api_change_count;
    }

    public int getApi_change_count() {
        return api_change_count;
    }

    public void setApi_change_count(int api_change_count) {
        this.api_change_count = api_change_count;
    }
}
