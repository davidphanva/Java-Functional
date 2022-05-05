package org.example.sceanrio.supplier;

import org.example.data.HouseProviderV1;
import org.example.model.Color;
import org.example.model.House;
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

        final List<House> neighborhood = HouseProviderV1.neighborhood();

        Print.showNeighborhood(neighborhood);

        final Random RANDOM = new Random();
        Supplier<Color> randomDecisionMaker = () -> {

            return Color.values()[RANDOM.nextInt(Color.values().length)];
        };

        BiConsumer<House, Supplier<Color>> painter = (house, decisionMaker) -> {

            house.setColor(decisionMaker.get());
        };

        performWorkOnHouses(neighborhood, painter, randomDecisionMaker);

        Print.showNeighborhood(neighborhood);
    }

    /**
     * In ScenarioV12, we had 3 static methods - one for each function (painting, house repair, &
     * deck building.  Here we have a single method, but we use the Function interface to
     * chain the supporting functions together.
     */
    public static void performWorkOnHouses(
            List<House> houses,
            BiConsumer<House, Supplier<Color>> painter,
            Supplier<Color> randomDecisionMaker) {

        for (House house : houses) {

            painter.accept(house, randomDecisionMaker);
        }
    }

    /**
     * In ScenarioV12 we used the Consumer interface, which performs something but does not
     * return anything.  What if we need a function that takes nothing and provides something.
     * See ScenarioV14.
     */
}
