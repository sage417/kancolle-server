package com.kancolle.server.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Package: com.kancolle.server.config
 * Author: mac
 * Date: 16/8/17
 */
@Configuration
public class DbConfig {

    @Bean(name = "mongoClient", destroyMethod = "close")
    MongoClient getMongoClient() {
        return new MongoClient("localhost");
    }
}
