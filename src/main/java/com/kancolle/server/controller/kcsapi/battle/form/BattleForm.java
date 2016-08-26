/**
 * 
 */
package com.kancolle.server.controller.kcsapi.battle.form;

import javax.validation.constraints.Min;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 *
 */
public class BattleForm {

    @Min(value = 1L)
    private int api_formation;

    @Min(value = 0L)
    private int api_recovery_type;

    public int getApi_formation() {
        return api_formation;
    }

    public void setApi_formation(int api_formation) {
        this.api_formation = api_formation;
    }

    public int getApi_recovery_type() {
        return api_recovery_type;
    }

    public void setApi_recovery_type(int api_recovery_type) {
        this.api_recovery_type = api_recovery_type;
    }
}
