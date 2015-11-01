/**
 * 
 */
package com.kancolle.server.utils.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * @author J.K.SAGE
 * @Date 2015年11月1日
 *
 */
public class StringUnicodeModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    @Override
    public void setupModule(SetupContext context) {
        this.addSerializer(new StringUnicodeSerializer());
        super.setupModule(context);
    }
}
