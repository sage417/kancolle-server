package com.kancolle.server.dao.base.impl;

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
import java.sql.ResultSet;
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
        Class<T> entityClass = (Class<T>) DaoUtils.getSuperClassGenricType(this.getClass());
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

    protected <E> List<E> queryForModels(Class<E> clazz, String sql) {
        return queryForModels(clazz, sql, null);
    }

    protected <E> List<E> queryForModels(Class<E> clazz, String sql, Map<String, Object> params) {
        return getTemplate().query(sql, params, (rs, rn) -> this.setObject(clazz, rs));
    }

    protected <E> E queryForSingleModel(Class<E> clazz, String sql, Map<String, Object> params) {
        try {
            return template.queryForObject(sql, params, (rs, rn) -> this.setObject(clazz, rs));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private <V> V setObject(Class<V> clazz, ResultSet rs) {
        V instance = null;
        try {
            instance = clazz.newInstance();
            DaoUtils.setObject(instance, rs);
        } catch (Exception e) {
            LOGGER.error("Error Happen when queryForSingleModel", e);
        }
        return instance;
    }

    protected String generateJsonArray(Iterable<?> parts) {
        return "[" + Joiner.on(',').join(parts) + ",]";
    }
}
