/**
 *
 */
package com.kancolle.server.model.po.common.handler.immutableList;

import com.google.common.collect.ImmutableList;

import java.util.function.Function;

/**
 * @author J.K.SAGE
 * @Date 2015年5月30日
 */
public class IntegerImmutableListHandler extends ImmutableListHandler<Integer> {

    @Override
    protected Function<String, ImmutableList<Integer>> toImmutableList() {
        return toImmutableListFunction.apply(Integer.class);
    }
}
