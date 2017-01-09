package com.kancolle.server.config;

import com.google.common.collect.ImmutableList;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Package: com.kancolle.server.config
 * Author: mac
 * Date: 16/8/17
 */

@Configuration
public class MongoDbConfig {

    @Profile({"dev", "test"})
    @Bean(name = "mongoClient", destroyMethod = "close")
    MongoClient getDevMongoClient() {
        return new MongoClient(ImmutableList.of(new ServerAddress("192.168.1.100", 40000), new ServerAddress("192.168.1.100", 40001)));
    }

    @Profile({"prod","ci"})
    @Bean(name = "mongoClient", destroyMethod = "close")
    MongoClient getProdMongoClient() {
        return new MongoClient("mongodb");
    }
}
