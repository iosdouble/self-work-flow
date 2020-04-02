package com.nh.support.client.jdbc;

import com.nh.support.model.ObjectMap;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @Classname ObjectMapRowMapper
 * @Description TODO
 * @Date 2020/4/2 6:47 PM
 * @Created by nihui
 */
public class ObjectMapRowMapper implements RowMapper<ObjectMap> {
    @Override
    public ObjectMap mapRow(ResultSet rs, int rowNum) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        ObjectMap mapOfColValues = new ObjectMap();
        for (int i = 1; i <= columnCount; i++) {
            String key = JdbcUtils.lookupColumnName(rsmd, i);
            Object obj = JdbcUtils.getResultSetValue(rs, i, String.class);
            mapOfColValues.put(key, obj);
        }
        return mapOfColValues;
    }
}
