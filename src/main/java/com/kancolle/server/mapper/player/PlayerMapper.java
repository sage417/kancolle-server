package com.kancolle.server.mapper.player;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PlayerMapper implements RowMapper<Player> {

    @Override
    public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
        Player player = new Player();
        player.setPlayer_id(rs.getString(1));
        player.setApi_token(rs.getString(2));
        player.setCreate_time(rs.getDate(3));
        return player;
    }
}
