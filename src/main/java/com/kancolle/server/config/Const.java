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
        return new ImmutableList.Builder<Integer>()
                .add(10)
                .add(11)
                .add(12)
                .add(52)
                .add(54)
                .add(55)
                .add(56)
                .add(57)
                .add(58)
                .add(59)
                .add(60)
                .add(61)
                .add(62)
                .add(63)
                .build();
        //return new int[]{10, 11, 12, 52, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63};
    }

    @Bean(name = "equip_exslot")
    public ImmutableList<Integer> equip_exslot() {
        return new ImmutableList.Builder<Integer>().add(23).add(43).add(44).build();
    }
}
