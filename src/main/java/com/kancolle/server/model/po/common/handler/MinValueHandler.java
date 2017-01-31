/**
 *
 */
package com.kancolle.server.model.po.common.handler;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

/**
 * @author J.K.SAGE
 * @Date 2015年5月30日
 */
public class MinValueHandler extends BaseTypeHandler<Integer> {
    private static final Function<String, Integer> toMaxMinValue = str -> {
        List<Integer> values = JSON.parseArray(str, Integer.class);
        return values.get(0);
    };

    @Override
    public Integer getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String rawStr = rs.getString(columnName);
        return toMaxMinValue.apply(rawStr);
    }

    @Override
    public Integer getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String rawStr = rs.getString(columnIndex);
        return toMaxMinValue.apply(rawStr);
    }

    @Override
    public Integer getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String rawStr = cs.getString(columnIndex);
        return toMaxMinValue.apply(rawStr);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int columnIndex, Integer value, JdbcType jdbcType) throws SQLException {
        ps.setString(columnIndex, value.toString());
    }
}
