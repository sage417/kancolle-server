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
import com.alibaba.fastjson.JSONObject;

/**
 * @author J.K.SAGE
 * @Date 2015年5月30日
 *
 */
public class JsonObjectHanlder extends BaseTypeHandler<JSONObject> {
    private static final Function<String, JSONObject> toJsonObject = str -> {
        return JSON.parseObject(str);
    };

    @Override
    public JSONObject getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toJsonObject.apply(rs.getString(columnName));
    }

    @Override
    public JSONObject getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toJsonObject.apply(rs.getString(columnIndex));
    }

    @Override
    public JSONObject getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toJsonObject.apply(cs.getString(columnIndex));
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int columnIndex, JSONObject value, JdbcType jdbcType) throws SQLException {
        ps.setString(columnIndex, value.toJSONString());
    }
}
