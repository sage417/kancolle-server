package com.kancolle.server.model.po.common.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by J.K.SAGE on 2016/4/8 0008.
 */
public class JsonNodeHandler extends BaseTypeHandler<JsonNode> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonNodeHandler.class);

    private static final String EMPTY_OBJECT_JSON = "{}";


    private JsonNode readNode(String node) {
        try {
            return OBJECT_MAPPER.readTree(node);
        } catch (IOException e) {
            return OBJECT_MAPPER.createObjectNode();
        }
    }

    @Override
    public JsonNode getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return readNode(rs.getString(columnName));
    }

    @Override
    public JsonNode getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return readNode(rs.getString(columnIndex));
    }

    @Override
    public JsonNode getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return readNode(cs.getString(columnIndex));
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int columnIndex, JsonNode object, JdbcType jdbcType) throws SQLException {
        String value;
        try {
            value = OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error("error when serial obj", e);
            value = EMPTY_OBJECT_JSON;
        }
        ps.setString(columnIndex, value);
    }
}
