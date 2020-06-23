package com.coderworld968.orm;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages={"com.coderworld968.common","com.coderworld968.orm"})
@EnableTransactionManagement
public class DSApplication {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active","local");
        new SpringApplicationBuilder(DSApplication.class).run(args);
    }
}
