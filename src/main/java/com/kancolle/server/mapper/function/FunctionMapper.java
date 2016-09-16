package com.kancolle.server.mapper.function;

import org.apache.ibatis.annotations.Mapper;

/**
 * Package: com.kancolle.server.mapper.function
 * Author: mac
 * Date: 16/8/9
 */
@Mapper
public interface FunctionMapper {

    void disableForeignKeyChecks();

    void enableForeignKeyChecks();

}
