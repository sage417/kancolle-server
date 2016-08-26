/**
 * 
 */
package com.kancolle.server.model.po.common.handler;

import com.alibaba.fastjson.JSON;
import com.google.common.primitives.Longs;
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
public class LongJsonArrayHandler extends BaseTypeHandler<long[]> {
    private static final Function<String, long[]> toLongArray = str ->
            Longs.toArray(JSON.parseArray(str, Long.class));

    @Override
    public long[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toLongArray.apply(rs.getString(columnName));
    }

    @Override
    public long[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toLongArray.apply(rs.getString(columnIndex));
    }

    @Override
    public long[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toLongArray.apply(cs.getString(columnIndex));
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int columnIndex, long[] value, JdbcType jdbcType) throws SQLException {
        ps.setString(columnIndex, JSON.toJSONString(value));
    }
}
