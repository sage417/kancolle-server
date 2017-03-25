package com.kancolle.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Package: com.kancolle.server
 * Author: mac
 * Date: 16/8/26
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class KancolleApp {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(KancolleApp.class, args);
    }
}
