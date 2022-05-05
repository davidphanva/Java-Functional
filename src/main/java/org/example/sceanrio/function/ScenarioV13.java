package org.example.sceanrio.function;

import org.example.data.HouseProviderV1;
import org.example.model.Color;
import org.example.model.HomeStyle;
import org.example.model.House;
import org.example.model.State;
import org.example.util.Print;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * To use the Adapter Pattern we need use the Function interface
 */
public class ScenarioV13 {

    public static void main( String[] args ) {
        System.out.println("ScenarioV13");
        System.out.println("============================");

        final List<House> neighborhood = HouseProviderV1.neighborhood();

        Print.showNeighborhood(neighborhood);

        Function<House, House> painter = house -> {
            house.setColor(Color.GREEN);

            return house;
        };

        Function<House, House> repairman = house -> {
            if (house.getState() == State.BROKEN) {
                house.setState(State.FUNCTIONAL);
            }

            return house;
        };

        Function<House, House> deckBuilder = house -> {
            if (house.getHomeStyle() != HomeStyle.HISTORIC)
                house.addDeck();

            return house;
        };

        Function<House, House> constructionCompany = painter.andThen(repairman).andThen(deckBuilder);

        performWorkOnHouses(neighborhood, constructionCompany);

        Print.showNeighborhood(neighborhood);
    }

    /**
     * In ScenarioV12, we had 3 static methods - one for each function (painting, house repair, &
     * deck building.  Here we have a single method, but we use the Function interface to
     * chain the supporting functions together.
     */
    public static void performWorkOnHouses(
            List<House> houses,
            Function<House, House> company) {

        for (House house : houses) {

            company.apply(house);
        }
    }

    /**
     * In ScenarioV12 we used the Consumer interface, which performs something but does not
     * return anything.  What if we need a function that takes nothing and provides something.
     * See ScenarioV14.
     */
}
