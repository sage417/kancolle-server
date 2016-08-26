/**
 * 
 */
package com.kancolle.server.model.po.common.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
public class JsonArrayHandler extends BaseTypeHandler<JSONArray> {
    private static final Function<String, JSONArray> toJsonArray = JSON::parseArray;

    @Override
    public JSONArray getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toJsonArray.apply(rs.getString(columnName));
    }

    @Override
    public JSONArray getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toJsonArray.apply(rs.getString(columnIndex));
    }

    @Override
    public JSONArray getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toJsonArray.apply(cs.getString(columnIndex));
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int columnIndex, JSONArray value, JdbcType jdbcType) throws SQLException {
        ps.setString(columnIndex, value.toJSONString());
    }
}
