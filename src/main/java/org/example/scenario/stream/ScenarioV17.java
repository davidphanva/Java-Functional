package org.example.scenario.stream;

import java.util.Random;
import java.util.stream.Stream;

/**
 * Assume we have a large dataset in this scenario.  Let's take advantage
 * of the number or cores on our computer chips by utilizing the Stream
 * parallelism capability.
 */
public class ScenarioV17 {

    public static void main( String[] args ) {
        System.out.println("ScenarioV17");
        System.out.println("============================");

        final Random RANDOM = new Random();

/*
        Stream.generate(RANDOM::nextInt)
                .filter(number -> true)
                .map(number -> number)
                .forEach(number -> System.out.println(number));
                */

        // This is the same as the commented block above.  But it uses
        // Stream parallel mechanism.  No need for Thread management by us.
        Stream.generate(RANDOM::nextInt)
                .parallel()
                .filter(number -> true)
                .map(number -> number)
                .forEach(number -> System.out.println(number));
    }

    /**
     * So far we've seen some of Java 8 functional capabilities that enable us to
     * write more direct code, which focuses on what to do rather than how to do.
     * Functional programming is more than these capabilities.  It's a paradigm
     * that requires us to also adhere to a number of principles.  We'll take a
     * look at these next in ScenarioV18.
     */
}
