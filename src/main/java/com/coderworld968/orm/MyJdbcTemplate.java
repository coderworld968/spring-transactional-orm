package com.coderworld968.orm;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyJdbcTemplate {

    private DataSource dataSource;

    public MyJdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @SneakyThrows
    public void execute(String sql) {
        Connection connection = DatasourceUtils.getConnection(dataSource);
        log.debug("execute sql connection:{},sql:{}", connection, sql);
        Statement statement = connection.createStatement();
        boolean execute = statement.execute(sql);
        int updateCount = statement.getUpdateCount();
        log.debug("execute sql:{},updateCount:{}", sql, updateCount);
        statement.close();
        DatasourceUtils.closeConnection(dataSource,connection);
    }


    @SneakyThrows
    public <T> List<T> query(String sql, Class clazz) {
        Connection connection = DatasourceUtils.getConnection(dataSource);
        log.debug("query sql connection:{},sql:{}", connection, sql);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        ResultSetMetaData rsm = rs.getMetaData();
        int colNumber = rsm.getColumnCount();
        List list = new ArrayList();
        Field[] fields = clazz.getDeclaredFields();
        while (rs.next()) {
            Object obj = clazz.newInstance();
            for (int i = 1; i <= colNumber; i++) {
                Object value = rs.getObject(i);
                for (int j = 0; j < fields.length; j++) {
                    Field f = fields[j];
                    if (f.getName().equalsIgnoreCase(rsm.getColumnName(i))) {
                        boolean flag = f.isAccessible();
                        f.setAccessible(true);
                        f.set(obj, value);
                        f.setAccessible(flag);
                        break;
                    }
                }
            }
            list.add(obj);
        }
        rs.close();
        statement.close();
        DatasourceUtils.closeConnection(dataSource,connection);
        return list;
    }
}
