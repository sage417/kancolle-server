package com.kancolle.server.config;

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
    public int[] createUseItemIds() {
        return new int[]{10, 11, 12, 52, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63};
    }

    @Bean(name = "equip_exslot")
    public int[] equip_exslot() {
        return new int[]{23, 43, 44};
    }
}
