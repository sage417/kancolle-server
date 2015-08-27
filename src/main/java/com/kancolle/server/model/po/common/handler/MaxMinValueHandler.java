/**
 * 
 */
package com.kancolle.server.model.po.common.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.alibaba.fastjson.JSON;
import com.kancolle.server.model.po.common.MaxMinValue;

/**
 * @author J.K.SAGE
 * @Date 2015年5月30日
 *
 */
public class MaxMinValueHandler extends BaseTypeHandler<MaxMinValue> {
    private static final Function<String, MaxMinValue> toMaxMinValue = str -> {
        List<Integer> values = JSON.parseArray(str, Integer.class);
        return new MaxMinValue(values.get(0), values.get(1));
    };

    @Override
    public MaxMinValue getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String rawStr = rs.getString(columnName);
        return toMaxMinValue.apply(rawStr);
    }

    @Override
    public MaxMinValue getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String rawStr = rs.getString(columnIndex);
        return toMaxMinValue.apply(rawStr);
    }

    @Override
    public MaxMinValue getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String rawStr = cs.getString(columnIndex);
        return toMaxMinValue.apply(rawStr);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int columnIndex, MaxMinValue value, JdbcType jdbcType) throws SQLException {
        ps.setString(columnIndex, value.toString());
    }
}
