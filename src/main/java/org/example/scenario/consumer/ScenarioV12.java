package org.example.scenario.consumer;

import org.example.data.HouseProviderV1;
import org.example.model.Color;
import org.example.model.HomeStyle;
import org.example.model.House;
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

        final List<House> neighborhood = HouseProviderV1.neighborhood();

        Print.showNeighborhood(neighborhood);

        paintHouses(
                neighborhood,
                house -> house.setColor(Color.GREEN));
        fixHouses(
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
                    if (house.getHomeStyle() != HomeStyle.HISTORIC)
                        house.addDeck();
                }
        );

        Print.showNeighborhood(neighborhood);
    }

    public static void paintHouses(
            List<House> houses,
            Consumer<House> painter) {

        for (House house : houses) {

            painter.accept(house);
        }
    }

    public static void fixHouses(
            List<House> houses,
            Consumer<House> repairman) {

        for (House house : houses) {

            repairman.accept(house);
        }
    }

    public static void buildDecks(
            List<House> houses,
            Consumer<House> deckBuilder) {

        for (House house : houses) {

            deckBuilder.accept(house);
        }
    }

    /**
     * But what if we want to have a painter paint the house, then once that's done
     * the painter "passes" the house to a repairman, who then "passes" the house
     * to the deck builder?  In other words, use the Adapter Pattern in a
     * functional way.  Can you the code to implement this?  See ScenarioV13.
     */
}
