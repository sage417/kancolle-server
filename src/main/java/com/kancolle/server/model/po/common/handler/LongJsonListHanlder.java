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

/**
 * @author J.K.SAGE
 * @Date 2015年5月30日
 *
 */
public class LongJsonListHanlder extends BaseTypeHandler<List<Long>> {
    private static final Function<String, List<Long>>toLongArray = str->{
        return JSON.parseArray(str, Long.class);
    };
    /* (non-Javadoc)
     * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet, java.lang.String)
     */
    @Override
    public List<Long> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toLongArray.apply(rs.getString(columnName));
    }

    /* (non-Javadoc)
     * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet, int)
     */
    @Override
    public List<Long> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toLongArray.apply(rs.getString(columnIndex));
    }

    /* (non-Javadoc)
     * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.CallableStatement, int)
     */
    @Override
    public List<Long> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toLongArray.apply(cs.getString(columnIndex));
    }

    /* (non-Javadoc)
     * @see org.apache.ibatis.type.BaseTypeHandler#setNonNullParameter(java.sql.PreparedStatement, int, java.lang.Object, org.apache.ibatis.type.JdbcType)
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int columnIndex, List<Long> value, JdbcType jdbcType) throws SQLException {
        ps.setString(columnIndex, JSON.toJSONString(value));
    }

}
