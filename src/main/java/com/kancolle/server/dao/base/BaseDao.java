package com.kancolle.server.dao.base;

import java.io.Serializable;

public interface BaseDao<T extends Serializable> {

    default void save(T t) {
        throw new UnsupportedOperationException();
    }

    default void replace(T t) {
        throw new UnsupportedOperationException();
    }

    default void update(T t) {
        throw new UnsupportedOperationException();
    }

    default void update(T t, String... columns) {
        throw new UnsupportedOperationException();
    }

    default void delete(T t) {
        throw new UnsupportedOperationException();
    }

}
