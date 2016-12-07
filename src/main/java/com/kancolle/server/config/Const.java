package com.kancolle.server.config;

import com.google.common.collect.ImmutableList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Package: com.kancolle.server.config
 * Author: mac
 * Date: 16/8/8
 */
@Configuration
public class Const {

    @Bean(name = "useItemIds")
    public ImmutableList<Integer> createUseItemIds() {
        return ImmutableList.of(10, 11, 12, 52, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63);
    }

    @Bean(name = "equip_exslot")
    public ImmutableList<Integer> equip_exslot() {
        return ImmutableList.of(23, 43, 44);
    }
}
