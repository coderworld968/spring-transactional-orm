package com.coderworld968.jdbc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages={"com.coderworld968.common","com.coderworld968.jdbc"})
@EnableTransactionManagement
public class Application  {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .run(args);
    }

}