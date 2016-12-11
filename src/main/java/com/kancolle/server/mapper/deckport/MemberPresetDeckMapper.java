package com.kancolle.server.mapper.deckport;

import com.kancolle.server.model.po.deckport.PresetDeck;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by J.K.SAGE on 2016/8/15 0015.
 */
@Mapper
public interface MemberPresetDeckMapper {

    List<PresetDeck> selectPresetDeckByMemberId(@Param("member_id") long member_id);

    PresetDeck getPresetDeckByMemberIdAndNo(@Param("member_id") long member_id, @Param("preset_no") int api_preset_no);

    int updatePresetDeck(PresetDeck presetDeck);

    int insertPresetDecks(List<PresetDeck> presetDecks);
}
