package org.example.scenario.supplier;

import org.example.data.HutProviderV1;
import org.example.model.Color;
import org.example.model.Hut;
import org.example.util.Print;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * Painting all houses green doesn't seem like the right thing to do.
 * Perhaps, a home owner needs to make a decision on the color.
 * Let's assume that home owners randomly selects a color to paint
 * their houses.
 */
public class ScenarioV14 {

    public static void main( String[] args ) {
        System.out.println("ScenarioV14");
        System.out.println("============================");

        final List<Hut> neighborhood = HutProviderV1.neighborhood();

        Print.showHuts(neighborhood);

        final Random RANDOM = new Random();
        Supplier<Color> randomDecisionMaker = () -> {

            return Color.values()[RANDOM.nextInt(Color.values().length)];
        };

        BiConsumer<Hut, Supplier<Color>> painter = (house, decisionMaker) -> {

            house.setColor(decisionMaker.get());
        };

        performWorkOnHouses(neighborhood, painter, randomDecisionMaker);

        Print.showHuts(neighborhood);
    }

    /**
     * In ScenarioV12, we had 3 static methods - one for each function (painting, house repair, &
     * deck building.  Here we have a single method, but we use the Function interface to
     * chain the supporting functions together.
     */
    public static void performWorkOnHouses(
            List<Hut> huts,
            BiConsumer<Hut, Supplier<Color>> painter,
            Supplier<Color> randomDecisionMaker) {

        for (Hut hut : huts) {

            painter.accept(hut, randomDecisionMaker);
        }
    }

    /**
     * In a typical application, one might find code that does something like the following:
     *
     * Step 1: Read data from a source and build a collection of data.
     *
     * Step 2: Iterate through that collection and perform something on each data object (paint each house).
     *
     * Step 3: Iterate through the same collection and perform some other task on each data object
     * (e.g., fix each house that's broken)
     *
     * Step 4: Iterate through the same collection again and derive something from each data object
     * (e.g., count the number of residents from each house).
     *
     * Can this process be applied to all applications - meaning iterate through a collection several times?
     * We'll examine this question in ScenarioV15.
     */
}
