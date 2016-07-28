/**
 *
 */
package com.kancolle.server.model.po.common.handler;

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
 * @author J.K.SAGE
 * @Date 2015年5月30日
 */
public class ImmutableIntListHandler extends BaseTypeHandler<ImmutableList<Integer>> {
    private static final Function<String, ImmutableList<Integer>> toImmutableIntList = str ->
            ImmutableList.copyOf(JSON.parseArray(str, Integer.class));

    @Override
    public ImmutableList<Integer> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toImmutableIntList.apply(rs.getString(columnName));
    }

    @Override
    public ImmutableList<Integer> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toImmutableIntList.apply(rs.getString(columnIndex));
    }

    @Override
    public ImmutableList<Integer> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toImmutableIntList.apply(cs.getString(columnIndex));
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int columnIndex, ImmutableList<Integer> value, JdbcType jdbcType) throws SQLException {
        throw new UnsupportedOperationException();
    }
}
