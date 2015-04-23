package com.kancolle.server.dao.player;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.mapper.player.Player;

public interface PlayerDao extends BaseDao<Player> {

    Player getPlayerByApiToken(String api_token);

}
