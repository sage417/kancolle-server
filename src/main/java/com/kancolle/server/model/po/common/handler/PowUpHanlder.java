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
import com.kancolle.server.model.po.common.PowUpValue;

/**
 * @author J.K.SAGE
 * @Date 2015年5月30日
 *
 */
public class PowUpHanlder extends BaseTypeHandler<PowUpValue> {
    private static final Function<String, PowUpValue> toPowUpValue = str->{
        List<Integer> values = JSON.parseArray(str, Integer.class);
        return new PowUpValue(values.get(0), values.get(1), values.get(2), values.get(3));
    };

    /* (non-Javadoc)
     * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet, java.lang.String)
     */
    @Override
    public PowUpValue getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String raw = rs.getString(columnName);
        return toPowUpValue.apply(raw);
    }

    /* (non-Javadoc)
     * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet, int)
     */
    @Override
    public PowUpValue getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String raw = rs.getString(columnIndex);
        return toPowUpValue.apply(raw);
    }

    /* (non-Javadoc)
     * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.CallableStatement, int)
     */
    @Override
    public PowUpValue getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String raw = cs.getString(columnIndex);
        return toPowUpValue.apply(raw);
    }

    /* (non-Javadoc)
     * @see org.apache.ibatis.type.BaseTypeHandler#setNonNullParameter(java.sql.PreparedStatement, int, java.lang.Object, org.apache.ibatis.type.JdbcType)
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int columnIndex, PowUpValue value, JdbcType jdbcType) throws SQLException {
        ps.setString(columnIndex, value.toString());
    }

}
