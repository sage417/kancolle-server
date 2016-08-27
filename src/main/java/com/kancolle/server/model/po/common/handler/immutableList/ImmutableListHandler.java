package com.kancolle.server.model.po.common.handler.immutableList;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableList;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

/**
 * Package: com.kancolle.server.model.po.common.handler
 * Author: mac
 * Date: 16/8/10
 */
public abstract class ImmutableListHandler<T> extends BaseTypeHandler<ImmutableList<T>> {

    protected final Function<Class<T>, Function<String, ImmutableList<T>>> toImmutableListFunction = clazz -> str ->
            ImmutableList.copyOf(JSON.parseArray(str, clazz));

    protected abstract Function<String, ImmutableList<T>> toImmutableList();


    @Override
    public void setNonNullParameter(PreparedStatement ps, int columnIndex, ImmutableList<T> value, JdbcType jdbcType) throws SQLException {
        ps.setString(columnIndex, JSON.toJSONString(value));
    }

    @Override
    public ImmutableList<T> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toImmutableList().apply(rs.getString(columnName));
    }

    @Override
    public ImmutableList<T> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toImmutableList().apply(rs.getString(columnIndex));
    }

    @Override
    public ImmutableList<T> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toImmutableList().apply(cs.getString(columnIndex));
    }
}
