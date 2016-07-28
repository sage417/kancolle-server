package com.kancolle.server.config;

import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Package: com.kancolle.server.config
 * Author: mac
 * Date: 16/7/27
 */
@Configuration
public class ServiceBeans {

    @Bean(name = "dutyBus")
    public EventBus getDutyEventBus() {
        return new EventBus();
    }
}
