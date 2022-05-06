package org.example.scenario.predicate;

import org.example.data.HouseProviderV1;
import org.example.model.HomeStyle;
import org.example.model.House;
import org.example.util.Print;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * In ScenarioV5 we're going to use java.util.function.Predicate
 * to replace HouseSearchCriterion.
 */
public class ScenarioV5 {

    public static void main( String[] args ) {
        System.out.println("ScenarioV5");
        System.out.println("============================");

        final List<House> neighborhood = HouseProviderV1.neighborhood();

        final Predicate<House> fourResidentCriterion =
                house -> house.getResidents().size() == 4;
        final List<House> fourResidents = searchByCriterion(neighborhood, fourResidentCriterion);
        Print.showNeighborhood(fourResidents);

        final Predicate<House> modernHomeCriterion =
                house -> house.getHomeStyle() == HomeStyle.MODERN;
        final List<House> modernHomes = searchByCriterion(neighborhood, modernHomeCriterion);
        Print.showNeighborhood(modernHomes);

        final Predicate<House> stoogiesHomeCriterion =
                house -> house.getAddress().equalsIgnoreCase("123 Comical Lane");
        final List<House> stoogiesHome = searchByCriterion(neighborhood, stoogiesHomeCriterion);
        Print.showNeighborhood(stoogiesHome);
    }

    public static List<House> searchByCriterion(List<House> houses, Predicate<House> criterion) {

        List<House> found = new ArrayList<>();

        for (House house : houses) {

            if (criterion.test(house))
                found.add(house);
        }

        return found;
    }

    /**
     * Now the code is more concise, while providing the same functionalities.
     * What if we want something more complex, like search for a modern home with
     * at least 4 residents?  See ScenarioV6.
     */
}
