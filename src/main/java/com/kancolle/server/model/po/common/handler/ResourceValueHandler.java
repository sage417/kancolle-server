/**
 * 
 */
package com.kancolle.server.model.po.common.handler;

import com.alibaba.fastjson.JSON;
import com.kancolle.server.model.po.common.ResourceValue;
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
 *
 */
public class ResourceValueHandler extends BaseTypeHandler<ResourceValue> {
    private static final Function<String, ResourceValue> toResourceValue = str -> {
        List<Integer> values = JSON.parseArray(str, Integer.class);
        return new ResourceValue(values.get(0), values.get(1), values.get(2), values.get(3));
    };

    @Override
    public ResourceValue getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String raw = rs.getString(columnName);
        return toResourceValue.apply(raw);
    }

    @Override
    public ResourceValue getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String raw = rs.getString(columnIndex);
        return toResourceValue.apply(raw);
    }

    @Override
    public ResourceValue getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String raw = cs.getString(columnIndex);
        return toResourceValue.apply(raw);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int columnIndex, ResourceValue value, JdbcType jdbcType) throws SQLException {
        ps.setString(columnIndex, value.toString());
    }
}
