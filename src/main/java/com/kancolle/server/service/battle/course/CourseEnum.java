package com.kancolle.server.service.battle.course;

/**
 * Created by J.K.SAGE on 2016/1/10 0010.
 */
public enum CourseEnum {

    SAME_COURSE(1), OPPOSITE_COURSE(2), T_COURSE_ADVANTAGE(3), T_COURSE_DISADVANTAGE(4);

    private final int index;

    CourseEnum(int courseIndex) {
        this.index = courseIndex;
    }

    public int getCourseIndex() {
        return this.index;
    }

    public static CourseEnum getCourseEnum(int courseIdx) {
        switch (courseIdx) {
            case 1:
                return SAME_COURSE;
            case 2:
                return OPPOSITE_COURSE;
            case 3:
                return T_COURSE_ADVANTAGE;
            case 4:
                return T_COURSE_DISADVANTAGE;
            default:
                throw new IllegalArgumentException("unknown course index");
        }
    }

    public static double shelllingHougAugment(int courseIdx) {
        CourseEnum courseEnum = getCourseEnum(courseIdx);
        switch (courseEnum) {
            case SAME_COURSE:
                return 0d;
            case OPPOSITE_COURSE:
                return -0.2d;
            case T_COURSE_ADVANTAGE:
                return 0.2d;
            case T_COURSE_DISADVANTAGE:
                return -0.4d;
            default:
                return 0d;
        }
    }
}
