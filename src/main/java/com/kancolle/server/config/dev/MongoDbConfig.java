package com.kancolle.server.config.dev;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Package: com.kancolle.server.config
 * Author: mac
 * Date: 16/8/17
 */
@Profile("dev")
@Configuration
public class MongoDbConfig {

    @Bean(name = "mongoClient", destroyMethod = "close")
    MongoClient getMongoClient() {
        return new MongoClient("localhost");
    }
}
