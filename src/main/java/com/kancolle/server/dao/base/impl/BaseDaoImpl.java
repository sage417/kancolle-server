package com.kancolle.server.dao.base.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.base.Joiner;
import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.utils.DaoUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public abstract class BaseDaoImpl<T extends Serializable> extends SqlSessionDaoSupport implements BaseDao<T> {
    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseDaoImpl.class);

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
    }

    ;

    @Override
    public void update(T t) {
        getSqlSession().update("update" + className, t);
    }

    public void save(T t) {
    }

    public void delete(T t) {
    }

    public void replace(T t) {
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
                LOGGER.error("Error Happen when queryForModels", e);
            }
            return instance;
        });
    }

    protected <E> E queryForSingleModel(Class<E> clazz, String sql, Map<String, Object> params) {
        try {
            return template.queryForObject(sql, params, (rs, rn) -> {
                E instance = null;
                try {
                    instance = clazz.newInstance();
                    DaoUtils.setObject(instance, rs);
                } catch (Exception e) {
                    LOGGER.error("Error Happen when queryForSingleModel", e);
                }
                return instance;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    protected String generateJsonArray(Iterable<?> parts) {
        return "[" + Joiner.on(',').join(parts) + ",]";
    }
}
