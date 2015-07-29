/**
 * 
 */
package com.kancolle.server.service.mission;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

/**
 * @author J.K.SAGE
 * @Date 2015年7月6日
 *
 */
public abstract class MissionResultCheckerFactory {
    private static final ApplicationContext APPLICATION_CONTEXT = ContextLoader.getCurrentWebApplicationContext();

    public static MissionResultChecker getMissionResultChecker(int mission_id) {
        return APPLICATION_CONTEXT.getBean(String.format("mission%dResultChecker", mission_id), MissionResultChecker.class);
    }
}
