package org.example.scenario.predicate;


@FunctionalInterface
public interface HouseSearchCriterion<H> {

    boolean matchesWith(H h);
}

/**
 * Compare the above interface to
 *
 * public interface Predicate<T> {
 *
 *     boolean test(T t);
 * }
 */
