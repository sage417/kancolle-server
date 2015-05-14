package com.kancolle.server.dao.mission;

import com.kancolle.server.dao.base.BaseDao;

public interface MissionDao<T> extends BaseDao<T>{

    /**
     * 回去mission時間
     * @param mission_id
     * @return int 分鐘
     */
    int getMissionTime(int mission_id);
    
    int updateDeckPortMission(String member_id, int deck_id, int mission_status,int mission_id,long mission_complete_time,int mission_flag);

}
