package org.example.scenario.function;

import org.example.data.HutProviderV1;
import org.example.model.Color;
import org.example.model.Style;
import org.example.model.Hut;
import org.example.model.State;
import org.example.util.Print;

import java.util.List;
import java.util.function.Function;

/**
 * To use the Adapter Pattern we need use the Function interface
 */
public class ScenarioV13 {

    public static void main( String[] args ) {
        System.out.println("ScenarioV13");
        System.out.println("============================");

        final List<Hut> neighborhood = HutProviderV1.neighborhood();

        Print.showHuts(neighborhood);

        Function<Hut, Hut> painter = hut -> {
            hut.setColor(Color.GREEN);

            return hut;
        };

        Function<Hut, Hut> repairman = hut -> {
            if (hut.getState() == State.BROKEN) {
                hut.setState(State.FUNCTIONAL);
            }

            return hut;
        };

        Function<Hut, Hut> deckBuilder = hut -> {
            if (hut.getStyle() != Style.HISTORIC)
                hut.addDeck();

            return hut;
        };

        Function<Hut, Hut> constructionCompany = painter.andThen(repairman).andThen(deckBuilder);

        performWorkOnHuts(neighborhood, constructionCompany);

        Print.showHuts(neighborhood);
    }

    /**
     * In ScenarioV12, we had 3 static methods - one for each function (painting, hut repair, &
     * deck building.  Here we have a single method, but we use the Function interface to
     * chain the supporting functions together.
     */
    public static void performWorkOnHuts(
            List<Hut> huts,
            Function<Hut, Hut> company) {

        for (Hut hut : huts) {

            company.apply(hut);
        }
    }

    /**
     * In ScenarioV12 we used the Consumer interface, which performs something but does not
     * return anything.  What if we need a function that takes nothing and provides something.
     * See ScenarioV14.
     */
}
