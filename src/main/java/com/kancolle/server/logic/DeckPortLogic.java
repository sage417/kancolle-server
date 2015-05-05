package com.kancolle.server.logic;

public class DeckPortLogic {

    public static boolean checkChange(int fleet_id, int ship_idx, long ship_id) {
        // 旗舰不能删除
        return !(fleet_id == 1 && ship_idx == 0 && ship_id == -1L);
    }
}
