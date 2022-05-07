package org.example.scenario.consumer;

import org.example.data.HutProviderV1;
import org.example.model.Color;
import org.example.model.Style;
import org.example.model.Hut;
import org.example.model.State;
import org.example.util.Print;

import java.util.List;
import java.util.function.Consumer;

/**
 * In this scenario, we'll explore the use of the Consumer interface.  So
 * what does this interface provide?  It represents those functions that
 * takes an object as input, does something, and returns nothing.  Sometimes,
 * software developers refer to this action as calling a method for the side-effect.
 *
 * Let's consider this example.  Given a House object, the owner of it may want
 * to repaint it, fix it, add a deck, etc.  The owner may hire a painter, a repairman,
 * or a deck builder for those things.  These make perfect use of the Consumer interface.
 */
public class ScenarioV12 {

    public static void main( String[] args ) {
        System.out.println("ScenarioV12");
        System.out.println("============================");

        final List<Hut> neighborhood = HutProviderV1.neighborhood();

        Print.showHuts(neighborhood);

        paintHuts(
                neighborhood,
                house -> house.setColor(Color.GREEN));
        fixHuts(
                neighborhood,
                house -> {
                    if (house.getState() == State.BROKEN) {
                        house.setState(State.FUNCTIONAL);
                    }
                }
        );

        buildDecks(
                neighborhood,
                house -> {
                    if (house.getStyle() != Style.HISTORIC)
                        house.addDeck();
                }
        );

        Print.showHuts(neighborhood);
    }

    public static void paintHuts(
            List<Hut> huts,
            Consumer<Hut> painter) {

        for (Hut hut : huts) {

            painter.accept(hut);
        }
    }

    public static void fixHuts(
            List<Hut> huts,
            Consumer<Hut> repairman) {

        for (Hut hut : huts) {

            repairman.accept(hut);
        }
    }

    public static void buildDecks(
            List<Hut> huts,
            Consumer<Hut> deckBuilder) {

        for (Hut hut : huts) {

            deckBuilder.accept(hut);
        }
    }

    /**
     * But what if we want to have a painter paint the hut, then once that's done
     * the painter "passes" the hut to a repairman, who then "passes" the hut
     * to the deck builder?  In other words, use the Adapter Pattern in a
     * functional way.  Can you the code to implement this?  See ScenarioV13.
     */
}
