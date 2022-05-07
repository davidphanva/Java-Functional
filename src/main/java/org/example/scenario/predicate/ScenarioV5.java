package org.example.scenario.predicate;

import org.example.data.HutProviderV1;
import org.example.model.Style;
import org.example.model.Hut;
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

        final List<Hut> neighborhood = HutProviderV1.neighborhood();

        final Predicate<Hut> fourResidentCriterion =
                hut -> hut.getResidents().size() == 4;
        final List<Hut> fourResidents = searchByCriterion(neighborhood, fourResidentCriterion);
        Print.showHuts(fourResidents);

        final Predicate<Hut> modernHomeCriterion =
                hut -> hut.getStyle() == Style.MODERN;
        final List<Hut> modernHomes = searchByCriterion(neighborhood, modernHomeCriterion);
        Print.showHuts(modernHomes);

        final Predicate<Hut> stoogiesHomeCriterion =
                hut -> hut.getAddress().equalsIgnoreCase("123 Comical Lane");
        final List<Hut> stoogiesHome = searchByCriterion(neighborhood, stoogiesHomeCriterion);
        Print.showHuts(stoogiesHome);
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
     * Now the code is more concise, while providing the same functionalities.
     * What if we want something more complex, like search for a modern home with
     * at least 4 residents?  See ScenarioV6.
     */
}
