package com.taf.sample.configuration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableMongoRepositories(basePackages = {"com.taf.sample.entities.mongo"})
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("#{@properties.getProperty('spring.data.mongodb.database')}")
    private String dbName;
    @Value("#{@properties.getProperty('spring.data.mongodb.host')}")
    private String dbHost;
    @Value("#{@properties.getProperty('spring.data.mongodb.port')}")
    private int dbPort;
    @Value("#{@properties.getProperty('mongodb.password')}")
    private String dbPsw;
    @Value("#{@properties.getProperty('mongodb.username')}")
    private String dbUsername;
    @Value("#{@properties.getProperty('spring.data.mongodb.authenticationDatabase')}")
    private String dbAuth;

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    @Override
    public @Bean
    Mongo mongo() {
        if (!dbUsername.isEmpty()) {
            List<ServerAddress> seeds = new ArrayList<>();
            seeds.add(new ServerAddress(dbHost, dbPort));
            List<MongoCredential> credentials = new ArrayList<>();
            MongoCredential userCredential = MongoCredential.createCredential(dbUsername, dbAuth,
                    dbPsw.toCharArray());
            credentials.add(userCredential);
            return new MongoClient(seeds, credentials);
        } else {
            return new MongoClient(dbHost, dbPort);
        }
    }

}
