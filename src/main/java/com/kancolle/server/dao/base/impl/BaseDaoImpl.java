package com.kancolle.server.dao.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.utils.DaoUtils;

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

    protected <E> List<E> query(Class<E> clazz, String sql) {
        return getTemplate().query(sql, (rs, rn) -> {
            E instance = null;
            try {
                instance = clazz.newInstance();
                DaoUtils.setObject(instance, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return instance;
        });
    }

    protected <E> E queryForModel(Class<E> clazz, String sql, Object... arg) {
        return getTemplate().queryForObject(sql, (rs, rn) -> {
            E instance = null;
            try {
                instance = clazz.newInstance();
                DaoUtils.setObject(instance, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return instance;
        }, arg);
    }

    protected <E> E queryForModel(Class<E> clazz, String sql) {
        return getTemplate().queryForObject(sql, (rs, rn) -> {
            E instance = null;
            try {
                instance = clazz.newInstance();
                DaoUtils.setObject(instance, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return instance;
        });
    }
}
