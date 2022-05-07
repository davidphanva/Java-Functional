package org.example.scenario.predicate;

import org.example.data.HutProviderV1;
import org.example.model.Style;
import org.example.model.Hut;
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

        final List<Hut> neighborhood = HutProviderV1.neighborhood();

        final HutSearchCriterion<Hut> fourResidentCriterion =
                hut -> hut.getResidents().size() == 4;
        final List<Hut> fourResidents = searchByCriterion(neighborhood, fourResidentCriterion);
        Print.showHuts(fourResidents);

        final HutSearchCriterion<Hut> modernHomeCriterion =
                hut -> hut.getStyle() == Style.MODERN;
        final List<Hut> modernHomes = searchByCriterion(neighborhood, modernHomeCriterion);
        Print.showHuts(modernHomes);

        final HutSearchCriterion<Hut> stoogiesHomeCriterion =
                hut -> hut.getAddress().equalsIgnoreCase("123 Comical Lane");
        final List<Hut> stoogiesHome = searchByCriterion(neighborhood, stoogiesHomeCriterion);
        Print.showHuts(stoogiesHome);
    }

    public static List<Hut> searchByCriterion(List<Hut> huts, HutSearchCriterion criterion) {

        List<Hut> found = new ArrayList<>();

        for (Hut hut : huts) {

            if (criterion.matchesWith(hut))
                found.add(hut);
        }

        return found;
    }

    /**
     * Can we do any better?  Yes, Java 8 already provides Predicate, which
     * is very similar to HutSearchCriterion.  It enables us to wrap some
     * logic that returns true/false, given an object.  See ScenarioV5.
     */
}
