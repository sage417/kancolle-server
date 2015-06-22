/**
 * 
 */
package com.kancolle.server.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 *
 */
public class DateUtils {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String format(Instant instant) {
        return FORMATTER.format(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()));
    }
}
