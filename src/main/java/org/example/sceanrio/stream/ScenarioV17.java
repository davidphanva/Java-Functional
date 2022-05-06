package org.example.sceanrio.stream;

import java.util.Random;
import java.util.stream.Stream;

/**
 * Assume we have a large dataset in this scenario.  Let's take advantage
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
}
