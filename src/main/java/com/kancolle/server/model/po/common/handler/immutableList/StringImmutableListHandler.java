package com.kancolle.server.model.po.common.handler.immutableList;

import com.google.common.collect.ImmutableList;

import java.util.function.Function;

/**
 * Package: com.kancolle.server.model.po.common.handler
 * Author: mac
 * Date: 16/8/10
 */
public class StringImmutableListHandler extends ImmutableListHandler<String> {

    @Override
    protected Function<String, ImmutableList<String>> toImmutableList() {
        return toImmutableListFunction.apply(String.class);
    }
}
