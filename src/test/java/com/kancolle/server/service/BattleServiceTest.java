package com.kancolle.server.service;

import com.kancolle.server.service.battle.shelling.template.MemberShipShellingSystem;
import com.kancolle.server.service.battle.shelling.template.ShellingTemplate;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

/**
 * Created by SAGE on 2016/10/3.
 */
public class BattleServiceTest {

    @Test
    public void testToCoverDamages() {
        final String suffix = ".1";
        final int[] dam1 = {20, 30};
        ShellingTemplate template = new MemberShipShellingSystem();

        BigDecimal[] result = ReflectionTestUtils.invokeMethod(template, "toCoverDamages", (Object) dam1);
        Assert.assertArrayEquals(new BigDecimal[]{new BigDecimal(dam1[0] + suffix), new BigDecimal(dam1[1] + suffix)}, result);

        final int[] dam2 = {0};
        result = ReflectionTestUtils.invokeMethod(template, "toCoverDamages", (Object) dam2);
        Assert.assertArrayEquals(new BigDecimal[]{new BigDecimal(dam2[0] + suffix)}, result);

        final int[] dam3 = {10, 0};
        result = ReflectionTestUtils.invokeMethod(template, "toCoverDamages", (Object) dam3);
        Assert.assertArrayEquals(new BigDecimal[]{new BigDecimal(dam3[0] + suffix), new BigDecimal(dam3[1] + suffix)}, result);

    }

}
