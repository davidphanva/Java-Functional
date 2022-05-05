package org.example.sceanrio.predicate;

import org.example.data.HouseProviderV1;
import org.example.model.HomeStyle;
import org.example.model.House;
import org.example.util.Print;

import java.util.Arrays;
import java.util.List;

/**
 * ScenarioV1 is about demonstrating a simple data model (House).
 * HouseProviderV1 is a factory that provides House objects to
 * illustrate searching and sorting mechanisms.  Let's do those
 * things next.  See ScenarioV2.
 */
public class ScenarioV1
{
    public static void main( String[] args ) {
        System.out.println("ScenarioV1");
        System.out.println("============================");

        final List<House> neighborhood = HouseProviderV1.neighborhood();

        Print.showNeighborhood(neighborhood);
    }
}
