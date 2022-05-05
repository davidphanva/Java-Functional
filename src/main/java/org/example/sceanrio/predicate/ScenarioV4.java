package org.example.sceanrio.predicate;

import org.example.data.HouseProviderV1;
import org.example.model.HomeStyle;
import org.example.model.House;
import org.example.util.Print;

import java.util.ArrayList;
import java.util.List;

/**
 * ScenarioV4 demonstrates the use lambda expressions so that we don't need
 * to create a class for each criterion.  By using lambda expressions, we
 * are solving the same search problem via functional approach, rather
 * than an object-oriented one.  In functional programming, we tend to
 * focus less on creating objects then performing some action and more
 * on the goal of what we try to achieve.
 */
public class ScenarioV4 {

    public static void main( String[] args ) {
        System.out.println("ScenarioV4");
        System.out.println("============================");

        final List<House> neighborhood = HouseProviderV1.neighborhood();

        final HouseSearchCriterion<House> fourResidentCriterion =
                house -> house.getResidents().size() == 4;
        final List<House> fourResidents = searchByCriterion(neighborhood, fourResidentCriterion);
        Print.showNeighborhood(fourResidents);

        final HouseSearchCriterion<House> modernHomeCriterion =
                house -> house.getHomeStyle() == HomeStyle.MODERN;
        final List<House> modernHomes = searchByCriterion(neighborhood, modernHomeCriterion);
        Print.showNeighborhood(modernHomes);

        final HouseSearchCriterion<House> stoogiesHomeCriterion =
                house -> house.getAddress().equalsIgnoreCase("123 Comical Lane");
        final List<House> stoogiesHome = searchByCriterion(neighborhood, stoogiesHomeCriterion);
        Print.showNeighborhood(stoogiesHome);
    }

    public static List<House> searchByCriterion(List<House> houses, HouseSearchCriterion criterion) {

        List<House> found = new ArrayList<>();

        for (House house : houses) {

            if (criterion.matchesWith(house))
                found.add(house);
        }

        return found;
    }

    /**
     * Can we do any better?  Yes, Java 8 already provides Predicate, which
     * is very similar to HouseSearchCriterion.  It enables us to wrap some
     * logic that returns true/false, given an object.  See ScenarioV5.
     */
}
