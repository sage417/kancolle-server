/**
 * 
 */
package com.kancolle.server.model.po.common.handler;

import com.alibaba.fastjson.JSON;
import com.google.common.primitives.Ints;
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
 *
 */
public class IntJsonArrayHandler extends BaseTypeHandler<int[]> {
    private static final Function<String, int[]> toIntArray = str ->
        Ints.toArray(JSON.parseArray(str, Integer.class));

    @Override
    public int[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toIntArray.apply(rs.getString(columnName));
    }

    @Override
    public int[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toIntArray.apply(rs.getString(columnIndex));
    }

    @Override
    public int[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toIntArray.apply(cs.getString(columnIndex));
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int columnIndex, int[] value, JdbcType jdbcType) throws SQLException {
        ps.setString(columnIndex, JSON.toJSONString(value));
    }

}
