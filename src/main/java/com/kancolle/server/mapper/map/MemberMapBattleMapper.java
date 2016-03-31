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

    void insertMemberMapBattleState(@Param("member_id") String member_id, @Param("deck_id") Integer deck_id, @Param("mapArea_id") int mapArea_id, @Param("map_no") int map_no);

    MemberMapBattleState selectMemberMapBattleState(@Param("member_id") String member_id);

    void update(@Param("state") MemberMapBattleState state, @Param("updateColumns") String... updateColumns);
}
