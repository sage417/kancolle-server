package com.kancolle.server.service.player;

import com.kancolle.server.mapper.player.Player;

public interface PlayerService {

    public Player getPlayerByApiToken(String api_token);
}
