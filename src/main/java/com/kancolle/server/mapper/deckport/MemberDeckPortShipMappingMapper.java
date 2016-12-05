package com.kancolle.server.mapper.deckport;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by SAGE on 2016/12/5.
 */
public interface MemberDeckPortShipMappingMapper {

    int insertMemberDeckPortShipMapping(@Param("member_id") long member_id, @Param("deck_id") int deck_id);

    int addShipToDeckPortShipMapping(@Param("member_id") long member_id, @Param("deck_id") int deck_id, @Param("member_ship_id") long member_ship_id);

    int replaceShipToDeckPortShipMapping(@Param("member_id") long member_id, @Param("deck_id") int deck_id, @Param("replace_member_ship_id") long replace_member_ship_id, @Param("member_ship_id") long member_ship_id);

    int removeShipFromDeckPortShipMapping(@Param("member_id") long member_id, @Param("deck_id") int deck_id, @Param("removeShips") List<Long> member_ship_id);

    int clearDeckPortShipMapping(@Param("member_id") long member_id, @Param("deck_id") int deck_id);
}
