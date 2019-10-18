package io.imulab.review.leetcode.support;

import java.lang.annotation.*;

/**
 * Marker annotation for problem metadata.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Question {

    /**
     * @return index of the problem
     */
    String index();

    /**
     * @return name of the problem
     */
    String name();

    /**
     * @return an array containing relevant links to the problem
     */
    String[] links();

    /**
     * @return an array containing relevant tags of the problem, describing its knowledge points.
     */
    String[] tags();

    /**
     * @return the difficulty level
     */
    Difficulty level();
}
