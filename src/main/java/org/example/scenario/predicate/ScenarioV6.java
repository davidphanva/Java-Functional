package org.example.scenario.predicate;

import org.example.data.HutProviderV1;
import org.example.model.Style;
import org.example.model.Hut;
import org.example.util.Print;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * In ScenarioV6 we're going to search for Hut objects that require complex
 * criteria.  And we use Predicate to specify the logic associated with that
 * complex criteria.
 */
public class ScenarioV6 {

    public static void main( String[] args ) {
        System.out.println("ScenarioV6");
        System.out.println("============================");

        final List<Hut> neighborhood = HutProviderV1.neighborhood();

        final Predicate<Hut> atLeast4ResidentsCriterion =
                hut -> hut.getResidents().size() >= 4;

        final List<Hut> atLeast4Residents = searchByCriterion(neighborhood, atLeast4ResidentsCriterion);
        Print.showHuts(atLeast4Residents);

        final Predicate<Hut> modernHomeCriterion =
                hut -> hut.getStyle() == Style.MODERN;
        final List<Hut> modernHomes = searchByCriterion(neighborhood, modernHomeCriterion);
        Print.showHuts(modernHomes);

        final Predicate<Hut> atLeast4ResidentsAndModernHomeCriterion = atLeast4ResidentsCriterion
                .and(modernHomeCriterion);
        final List<Hut> atLeast4ResidentsAndModernHome = searchByCriterion(
                neighborhood,
                atLeast4ResidentsAndModernHomeCriterion);
        Print.showHuts(atLeast4ResidentsAndModernHome);
    }

    public static List<Hut> searchByCriterion(List<Hut> huts, Predicate<Hut> criterion) {

        List<Hut> found = new ArrayList<>();

        for (Hut hut : huts) {

            if (criterion.test(hut))
                found.add(hut);
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
