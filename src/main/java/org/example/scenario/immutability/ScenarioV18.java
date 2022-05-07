package org.example.scenario.immutability;

import org.example.data.HouseProviderV1;
import org.example.model.Color;
import org.example.model.House;
import org.example.util.Print;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * In Java programming, and perhaps in any language, immutability means that objects
 * cannot be changed once they are created.  The advantages of having only immutable
 * objects in an application are:
 *
 * 1. Better security
 * 2. Easier to manage in concurrency programming
 * 3. Can lead to object reduction, if objects of the same values are used
 *
 * To create immutable objects, we need to follow the guidelines below.
 *
 * 1. Declare a class final so that it cannot be subclassed (none of its methods can be
 * overridden
 * 2. Declare all attributes final so once an object is created, its attributes cannot be
 * changed
 * 3. Provide getters for its attributes, but not setters
 *
 * Once a class is defined, we also need to deal with the question of how objects from
 * such class are created.  In general, a class should not have its constructors available
 * to its users.  Rather, factory methods should be provided to create such class' objects.
 * This prevents changing how objects are created from affecting where those objects are used.
 *
 * ScenarioV18 is an adaptation from ScenarioV14, in which setColor was used.  Some changes
 * are made in this scenario to adhere to the immutability principle.
 */
public class ScenarioV18 {

    public static void main( String[] args ) {
        System.out.println("ScenarioV18");
        System.out.println("============================");

        final List<House> neighborhood = HouseProviderV1.neighborhood();

        Print.showHouses(neighborhood);

        final Random RANDOM = new Random();
        Supplier<Color> randomDecisionMaker = () -> {

            return Color.values()[RANDOM.nextInt(Color.values().length)];
        };

        // Compared to ScenarioV14, we need to replace BiConsumer with BiFunction
        // in order to return a new House object, created from the setColor method
        BiFunction<House, Supplier<Color>, House> painter = (house, decisionMaker) -> {

            return house.setColor(decisionMaker.get());
        };

        List<House> betterNeighborhood = performWorkOnHouses(neighborhood, painter, randomDecisionMaker);

        Print.showHouses(betterNeighborhood);
    }

    public static List<House> performWorkOnHouses(
            List<House> houses,
            BiFunction<House, Supplier<Color>, House> painter,
            Supplier<Color> randomDecisionMaker) {

        List<House> workedOnHouses = new ArrayList<>();

        for (House house : houses) {

            workedOnHouses.add(painter.apply(house, randomDecisionMaker));
        }

        return workedOnHouses;
    }
}
