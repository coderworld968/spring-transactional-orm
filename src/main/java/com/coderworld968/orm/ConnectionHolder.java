package com.coderworld968.orm;

import java.sql.Connection;
import org.springframework.transaction.support.ResourceHolderSupport;

public class ConnectionHolder extends ResourceHolderSupport {

    private Connection connection;
    private boolean hasConnection = false;

    public ConnectionHolder(Connection connection) {
        this.connection = connection;
        if(connection != null){
            hasConnection = true;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean isHasConnection() {
        return hasConnection;
    }
}
