package com.coderworld968.orm;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class DataSourceConfiguration {


    @Autowired
    DataSourceProperties dataSourceProperties;

    @Bean
    public DataSource dataSource(DataSourceProperties properties) {
        Class<? extends DataSource> type = HikariDataSource.class;
        DataSource dataSource = properties.initializeDataSourceBuilder().type(type).build();
        log.info("DataSource:{}", dataSource);
        return dataSource;
    }

    @Bean
    public MyDataSourceTransactionManager myDataSourceTransactionManager(DataSource dataSource) {
        MyDataSourceTransactionManager myDataSourceTransactionManager = new MyDataSourceTransactionManager(
                dataSource);
        log.info("TransactionManager:{}", myDataSourceTransactionManager);
        return myDataSourceTransactionManager;
    }

    @Bean
    public MyJdbcTemplate myJdbcTemplate(DataSource dataSource) {
        return new MyJdbcTemplate(dataSource);
    }

}
