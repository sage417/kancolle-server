/**
 * 
 */
package com.kancolle.server.model.po.common.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
 * @author J.K.SAGE
 * @Date 2015年5月30日
 *
 */
public class JsonArrayHanlder extends BaseTypeHandler<JSONArray> {
    private static final Function<String, JSONArray> toJsonArray = str -> {
        return JSON.parseArray(str);
    };

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
