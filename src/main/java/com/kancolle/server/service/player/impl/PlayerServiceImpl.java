package com.kancolle.server.service.player.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kancolle.server.dao.player.PlayerDao;
import com.kancolle.server.mapper.player.Player;
import com.kancolle.server.service.player.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerDao playerDao;

    @Override
    public Player getPlayerByApiToken(String api_token) {
        return playerDao.getPlayerByApiToken(api_token);
    }
}
