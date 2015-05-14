package com.kancolle.server.service.deck.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kancolle.server.dao.deck.DeckPortDao;
import com.kancolle.server.model.kcsapi.member.MemberDeckPort;
import com.kancolle.server.service.deck.DeckPortService;

@Service
public class DeckPortServiceImpl implements DeckPortService {

    @Autowired
    private DeckPortDao deckPortDao;

    @Override
    public MemberDeckPort getMemberDeckPort(String member_id, int deck_id) {
        return deckPortDao.getMemberDeckPort(member_id, deck_id);
    }

}
