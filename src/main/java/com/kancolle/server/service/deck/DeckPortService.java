package com.kancolle.server.service.deck;

import com.kancolle.server.model.kcsapi.member.MemberDeckPort;

public interface DeckPortService {

    MemberDeckPort getMemberDeckPort(String member_id, int deck_id);

}
