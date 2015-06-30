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
import com.kancolle.server.model.po.mission.MissionItem;

/**
 * @author J.K.SAGE
 * @Date 2015年6月30日
 *
 */
public class MissionItemHandler extends BaseTypeHandler<MissionItem> {
    private static final Function<String, MissionItem> toMissionItem = str -> {
        JSONArray missionItemArray = JSON.parseArray(str);
        return new MissionItem(missionItemArray.getIntValue(0), missionItemArray.getIntValue(1));
    };

    @Override
    public void setNonNullParameter(PreparedStatement ps, int columnIndex, MissionItem parameter, JdbcType jdbcType) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public MissionItem getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toMissionItem.apply(rs.getString(columnName));
    }

    @Override
    public MissionItem getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toMissionItem.apply(rs.getString(columnIndex));
    }

    @Override
    public MissionItem getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toMissionItem.apply(cs.getString(columnIndex));
    }
}
