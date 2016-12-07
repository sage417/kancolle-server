package com.kancolle.server.utils.deckport;

import com.kancolle.server.utils.logic.MemberDeckPortShipUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Package: com.kancolle.server.utils.deckport
 * Author: mac
 * Date: 2016/12/7
 */
public class MemberDeckPortTest {

    @Test
    public void testFill() {

        long[] result = MemberDeckPortShipUtils.fillMemberDeckPortShipArray(new long[]{});
        Assert.assertArrayEquals(new long[]{-1, -1, -1, -1, -1, -1}, result);

        result = MemberDeckPortShipUtils.fillMemberDeckPortShipArray(new long[]{100});
        Assert.assertArrayEquals(new long[]{100, -1, -1, -1, -1, -1}, result);

        result = MemberDeckPortShipUtils.fillMemberDeckPortShipArray(new long[]{100, 101, 102, 103, 104, 105});
        Assert.assertArrayEquals(new long[]{100, 101, 102, 103, 104, 105}, result);
    }

    @Test(expected = NullPointerException.class)
    public void testNullPointer() {
        long[] result = MemberDeckPortShipUtils.fillMemberDeckPortShipArray(null);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgument() {
        long[] result = MemberDeckPortShipUtils.fillMemberDeckPortShipArray(new long[]{1, 2, 3, 4, 5, 6, 7});
    }

}
