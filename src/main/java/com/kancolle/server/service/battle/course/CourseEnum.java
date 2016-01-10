package com.kancolle.server.service.battle.course;

/**
 * Created by J.K.SAGE on 2016/1/10 0010.
 */
public enum CourseEnum {
    SAME_COURSE(1, 1.2d), OPPOSITE_COURSE(2, 1d), T_COURSE_ADVANTAGE(3, 0.8d), T_COURSE_DISADVANTAGE(4, 0.6d);

    private final int index;

    private final double augmenting;

    CourseEnum(int courseIndex, double augmenting) {
        this.index = courseIndex;
        this.augmenting = augmenting;
    }

    public int getCourseIndex() {
        return this.index;
    }

    public double getAugmenting() {
        return this.augmenting;
    }
}
