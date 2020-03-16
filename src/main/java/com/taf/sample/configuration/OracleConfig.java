package com.taf.sample.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.taf.sample.entities.oracle"})
@EnableJpaRepositories(basePackages = {"com.taf.sample.entities.oracle"})
public class OracleConfig {

    @Bean(name = "oracleConnection")
    public DataSource amsTechDataSource(@Value("#{@properties.getProperty('spring.datasource.url')}") String url,
                                        @Value("#{@properties.getProperty('spring.datasource.username')}") String username,
                                        @Value("#{@properties.getProperty('spring.datasource.password')}") String psw) {
        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(psw);
        return dataSource;
    }
}
