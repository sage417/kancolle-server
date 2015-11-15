/**
 * 
 */
package com.kancolle.server.service.battle.course;

import org.springframework.stereotype.Service;

import com.kancolle.server.utils.factory.FactoryUtils;

/**
 * @author J.K.SAGE
 * @Date 2015年9月2日
 *
 */
@Service
public class CourseSystemImpl implements CourseSystem {

    public static final int SAME_COURSE = 1;
    public static final int OPPOSITE_COURSE = 2;
    public static final int T_COURSE_ADVANTAGE = 3;
    public static final int T_COURSE_DISADVANTAGE = 4;

    private int[] cources = { SAME_COURSE, OPPOSITE_COURSE, T_COURSE_ADVANTAGE, T_COURSE_DISADVANTAGE };

    private final double[] course_pdf = { 45d, 30d, 15d, 10d };

    private final double[] course_cdf = FactoryUtils.pdf2cdf(course_pdf);

    @Override
    public int generateCourse() {
        int index = FactoryUtils.discrete(course_cdf);
        return cources[index];
    }
}
