package com.kancolle.server.dao.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.kancolle.server.dao.base.BaseDao;

public class BaseDaoImpl<T> implements BaseDao<T> {
	protected static final String SELECT_ALL = "SELECT * FROM ";

	protected String tableName;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	protected JdbcTemplate getTemplate() {
		return jdbcTemplate;
	}

	protected void setTableName(String tableName) {
		this.tableName = tableName;
	};

	protected String getTableName() {
		return this.tableName;
	}
}
