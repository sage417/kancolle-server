/**
 * 
 */
package com.kancolle.server.dao.lambda;

import java.util.function.Function;

/**
 * @author J.K.SAGE
 * @param <T>
 * @param <R>
 * @Date 2015年5月16日
 *
 */
@FunctionalInterface
public interface ThrowingFunction<T, R> extends Function<T, R> {

    @Override
    default R apply(final T elem) {
        try {
            return acceptThrows(elem);
        } catch (final Exception e) {
            System.out.println("handling an exception...");
            throw new RuntimeException(e);
        }
    }

    R acceptThrows(T elem) throws Exception;

}
