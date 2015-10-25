package com.kancolle.server.model.kcsapi.battle.ship;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class HougekiResultTest {

    @Test
    public void test() {
        HougekiResult result = new HougekiResult();
        String str = JSON.toJSONString(result);
        System.out.println(str);
    }

}
