package com.coderworld968.orm;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.SneakyThrows;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class DatasourceUtils {

    public static Connection getConnection(DataSource dataSource) throws SQLException {
        ConnectionHolder conHolder = (ConnectionHolder) TransactionSynchronizationManager
                .getResource(dataSource);
        Connection connection;
        if (null == conHolder || !conHolder.isHasConnection()) {
            connection = dataSource.getConnection();
        } else {
            connection = conHolder.getConnection();
        }
        return connection;
    }

    @SneakyThrows
    public static void closeConnection(DataSource dataSource, Connection connection){
        ConnectionHolder conHolder = (ConnectionHolder) TransactionSynchronizationManager
                .getResource(dataSource);
        if(null != conHolder){
            // do nothing
            return;
        }
        connection.close();
    }
}