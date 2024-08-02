package com.example.settlement_batch.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.write")
    public DataSourceProperties writeDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "writeDataSource")
    @Primary
    public DataSource writeDataSource() {
        return writeDataSourceProperties().initializeDataSourceBuilder().build();
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setJdbcUrl("jdbc:mysql://master:3306/settlement");
//        dataSource.setUsername("root");
//        dataSource.setPassword("password!");
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setMinimumIdle(20);
//        dataSource.setMaximumPoolSize(50);
//        dataSource.setIdleTimeout(30000);
//        dataSource.setConnectionTimeout(3000);
//        dataSource.setPoolName("WriteHikariCP");
//        dataSource.setMaxLifetime(1800000);
//        dataSource.setValidationTimeout(5000);
//
//        return new HikariDataSource(dataSource);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.read")
    public DataSourceProperties readDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "readDataSource")
    public DataSource readDataSource() {
        return readDataSourceProperties().initializeDataSourceBuilder().build();
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setJdbcUrl("jdbc:mysql://master:3306/settlement");
//        dataSource.setUsername("root");
//        dataSource.setPassword("password!");
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setMinimumIdle(20);
//        dataSource.setMaximumPoolSize(50);
//        dataSource.setIdleTimeout(30000);
//        dataSource.setConnectionTimeout(3000);
//        dataSource.setPoolName("WriteHikariCP");
//        dataSource.setMaxLifetime(1800000);
//        dataSource.setValidationTimeout(5000);
//
//        return new HikariDataSource(dataSource);
    }
}