/**
 * 
 */
package com.kancolle.server.model.kcsapi.member;

/**
 * @author J.K.SAGE
 * @Date 2015年7月27日
 *
 */
public class FurnitureCoinResult {

    private int api_coin;

    /**
     * @param getfCoin
     */
    public FurnitureCoinResult(int getfCoin) {
        this.api_coin = getfCoin;
    }

    public int getApi_coin() {
        return api_coin;
    }

    public void setApi_coin(int api_coin) {
        this.api_coin = api_coin;
    }
}
