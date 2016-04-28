/**
 *
 */
package com.kancolle.server.mapper.map;

import com.kancolle.server.model.po.battle.MemberMapBattleState;
import org.apache.ibatis.annotations.Param;

/**
 * @author J.K.SAGE
 * @Date 2015年8月21日
 *
 */
public interface MemberMapBattleMapper {

    String MAPCELL_ID = "mapCellId";
    String GET_RESOURCE = "getRecource";
    String FETCH_RESOURCE = "fetchRecource";
    String BATTLE_FLAG = "battleFlag";
    String RESULT_FLAG = "resultFlag";
    String SESSION = "session";

    void insertMemberMapBattleState(@Param("member_id") String member_id, @Param("deck_id") Integer deck_id, @Param("traveller_No") int traveller_No, @Param("map_no") int map_no);

    MemberMapBattleState selectMemberMapBattleState(@Param("member_id") String member_id);

    void update(@Param("state") MemberMapBattleState state, @Param("updateColumns") String... updateColumns);
}
