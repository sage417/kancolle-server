package com.kancolle.server.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Package: com.kancolle.server.utils
 * Author: mac
 * Date: 16/4/14
 */
public abstract class LoginUtils {

    private static final Pattern PATTERN = Pattern.compile("-", Pattern.LITERAL);

    public static String generateMemberToken(){
        return PATTERN.matcher(UUID.randomUUID().toString()).replaceAll(StringUtils.EMPTY);
    }
}
