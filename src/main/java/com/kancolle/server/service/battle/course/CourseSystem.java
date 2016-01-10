/**
 * 
 */
package com.kancolle.server.service.battle.course;

import com.kancolle.server.utils.factory.FactoryUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author J.K.SAGE
 * @Date 2015年9月2日
 *
 */
@Service
public class CourseSystem implements ICourseSystem {

    private final double[] course_pdf = { 45d, 30d, 15d, 10d };
    private final double[] course_cdf = FactoryUtils.pdf2cdf(course_pdf);
    private int[] courses = Arrays.stream(CourseEnum.values()).mapToInt(CourseEnum::getCourseIndex).toArray();

    @Override
    public int generateCourse() {
        int index = FactoryUtils.discrete(course_cdf);
        return courses[index];
    }
}
