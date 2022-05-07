package org.example.scenario.predicate;


@FunctionalInterface
public interface HutSearchCriterion<H> {

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
