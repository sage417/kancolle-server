package com.kancolle.server.logic;

public class ShipLogic {

    public static boolean checkDestory(String member_id, long api_ship_id) {
        // 入渠不能，远征不能，出击不能，第一舰队旗舰不能，只剩下一艘不能，加锁不能，装备加锁装备不能。。。
        return false;
    }
}
