package com.kancolle.server.dao.base;

import java.io.Serializable;

public interface BaseDao<T extends Serializable> {

    void update(T t);

}
