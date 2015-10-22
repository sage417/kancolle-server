package com.kancolle.server.dao.base;

import java.io.Serializable;

public interface BaseDao<T extends Serializable> {

    void save(T t);

    void replace(T t);

    void update(T t);

    void delete(T t);

}
