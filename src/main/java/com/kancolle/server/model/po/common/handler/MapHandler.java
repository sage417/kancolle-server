package com.kancolle.server.model.po.common.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Package: com.kancolle.server.model.po.common.handler
 * Author: mac
 * Date: 16/8/20
 */
public class MapHandler<K, V> extends BaseTypeHandler<Map<K, V>> {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    protected <K, V> Map<K, V> toMapFunction(String content) {
        try {
            return OBJECT_MAPPER.readValue(content, new TypeReference<Map>() {
            });
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Map parameter, JdbcType jdbcType) throws SQLException {
    }

    @Override
    public Map<K, V> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toMapFunction(rs.getString(columnName));
    }

    @Override
    public Map<K, V> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toMapFunction(rs.getString(columnIndex));
    }

    @Override
    public Map<K, V> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toMapFunction(cs.getString(columnIndex));
    }
}
