package com.kancolle.server.dao.base.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.utils.DaoUtils;

public class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T> {
    protected static final String SELECT_ALL = "SELECT * FROM ";

    private String className;

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        Class<T> entityClass = (Class<T>) params[0];
        className = entityClass.getSimpleName();
    }

    protected NamedParameterJdbcTemplate getTemplate() {
        return template;
    };

    public void update(T t) {
        getSqlSession().update("update" + className, t);
    }

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
}
