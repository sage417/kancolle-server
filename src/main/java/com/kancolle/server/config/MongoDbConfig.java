package com.kancolle.server.config;

import com.google.common.collect.ImmutableList;
import com.kancolle.server.model.mongo.MemberBattleFleet;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Profile("ci")
    @Bean(name = "mongoClient", destroyMethod = "close")
    MongoClient getCIMongoClient() {
        return new MongoClient("127.0.0.1");
    }

    @Profile("prod")
    @Bean(name = "mongoClient", destroyMethod = "close")
    MongoClient getProdMongoClient() {
        return new MongoClient("mongodb");
    }

    @Bean
    Datastore datastore(@Autowired MongoClient mongoClient) {
        final Morphia morphia = new Morphia();
        // tell Morphia where to find your classes
        // can be called multiple times with different packages or classes
        morphia.map(MemberBattleFleet.class);
        // create the Datastore connecting to the default port on the local host
        final Datastore datastore = morphia.createDatastore(mongoClient, "kancolle");
        datastore.ensureIndexes();
        return datastore;
    }
}
