package com.kancolle.server.dao.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.kancolle.server.dao.base.BaseDao;

public class BaseDaoImpl<T> implements BaseDao<T> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    protected JdbcTemplate getTemplate() {
        return jdbcTemplate;
    }
}
