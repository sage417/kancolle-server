package com.kancolle.server.utils.logic;

import com.google.common.base.Preconditions;
import com.kancolle.server.model.po.deckport.MemberDeckPort;

import java.util.Arrays;

/**
 * Package: com.kancolle.server.utils.logic
 * Author: mac
 * Date: 2016/12/7
 */
public abstract class MemberDeckPortShipUtils {

    public static long[] fillMemberDeckPortShipArray(final long[] ships) {
        Preconditions.checkNotNull(ships);
        Preconditions.checkArgument(ships.length <= MemberDeckPort.MAX_SHIP_COUNT);

        final long[] result = new long[MemberDeckPort.MAX_SHIP_COUNT];

        System.arraycopy(ships, 0, result, 0, ships.length);
        Arrays.fill(result, ships.length, MemberDeckPort.MAX_SHIP_COUNT, -1L);

        return result;
    }
}
