package org.example.scenario.predicate;

import org.example.data.HouseProviderV1;
import org.example.model.HomeStyle;
import org.example.model.House;
import org.example.util.Print;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * In ScenarioV6 we're going to search for House objects that require complex
 * criteria.  And we use Predicate to specify the logic associated with that
 * complex criteria.
 */
public class ScenarioV6 {

    public static void main( String[] args ) {
        System.out.println("ScenarioV6");
        System.out.println("============================");

        final List<House> neighborhood = HouseProviderV1.neighborhood();

        final Predicate<House> atLeast4ResidentsCriterion =
                house -> house.getResidents().size() >= 4;

        final List<House> atLeast4Residents = searchByCriterion(neighborhood, atLeast4ResidentsCriterion);
        Print.showNeighborhood(atLeast4Residents);

        final Predicate<House> modernHomeCriterion =
                house -> house.getHomeStyle() == HomeStyle.MODERN;
        final List<House> modernHomes = searchByCriterion(neighborhood, modernHomeCriterion);
        Print.showNeighborhood(modernHomes);

        final Predicate<House> atLeast4ResidentsAndModernHomeCriterion = atLeast4ResidentsCriterion
                .and(modernHomeCriterion);
        final List<House> atLeast4ResidentsAndModernHome = searchByCriterion(
                neighborhood,
                atLeast4ResidentsAndModernHomeCriterion);
        Print.showNeighborhood(atLeast4ResidentsAndModernHome);
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
     * So what is a Predicate?  Essentially, it is a function that performs some logic
     * and then returns a boolean.  Since the logic is nicely "wrapped" in an object
     * or a lambda, we can pass this logic to where it is needed.
     *
     * What if we need to have a function that returns something other than a boolean,
     * like an object?  See ScenarioV7.
     */
}
