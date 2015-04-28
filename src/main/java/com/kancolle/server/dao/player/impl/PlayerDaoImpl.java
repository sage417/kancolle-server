package com.kancolle.server.dao.player.impl;

import org.springframework.stereotype.Repository;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.player.PlayerDao;
import com.kancolle.server.mapper.player.Player;
import com.kancolle.server.mapper.player.PlayerMapper;

@Repository
public class PlayerDaoImpl extends BaseDaoImpl<Player> implements PlayerDao {

	@Override
	public Player getPlayerByApiToken(String api_token) {
		return getTemplate().queryForObject(
				"SELECT * from t_member where member_api_token = ?",
				new Object[] { api_token }, new PlayerMapper());
	}
}
