package com.kancolle.server.dao.base.impl;

import com.google.common.base.Joiner;
import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.utils.DaoUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.sql.ResultSet;

public abstract class BaseDaoImpl<T extends Serializable> extends SqlSessionDaoSupport implements BaseDao<T> {
    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseDaoImpl.class);

    private String className;

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
