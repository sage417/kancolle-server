package com.kancolle.server.dao.base.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.utils.DaoUtils;

public class BaseDaoImpl<T> implements BaseDao<T> {
    protected static final String SELECT_ALL = "SELECT * FROM ";

    protected String tableName;

    @Autowired
    private NamedParameterJdbcTemplate template;

    protected String getTableName() {
        return this.tableName;
    }

    protected NamedParameterJdbcTemplate getTemplate() {
        return template;
    };

    /**
     * 解析数据库字符串，返回JSONArray对象
     * 
     * @param sql
     * @param params
     * @return
     */
    protected JSONArray parseJSONArray(String sql, Map<String, Object> params) {
        return JSON.parseArray(template.queryForObject(sql, params, String.class));
    }

    protected <E> List<E> queryForModels(Class<E> clazz, String sql) {
        return queryForModels(clazz, sql, null);
    }

    protected <E> List<E> queryForModels(Class<E> clazz, String sql, Map<String, Object> params) {
        return getTemplate().query(sql, params, (rs, rn) -> {
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

    protected <E> E queryForSingleModel(Class<E> clazz, String sql) {
        return queryForSingleModel(clazz, sql);
    }

    protected <E> E queryForSingleModel(Class<E> clazz, String sql, Map<String, Object> params) {
        try {
            return template.queryForObject(sql, params, (rs, rn) -> {
                E instance = null;
                try {
                    instance = clazz.newInstance();
                    DaoUtils.setObject(instance, rs);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return instance;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    protected void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
