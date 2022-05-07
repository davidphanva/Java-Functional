package org.example.scenario.predicate;

import org.example.data.HutProviderV1;
import org.example.model.Hut;
import org.example.util.Print;

import java.util.List;

/**
 * ScenarioV1 is about demonstrating a simple data model (Hut).
 * HutProviderV1 is a factory that provides Hut objects to
 * illustrate searching and sorting mechanisms.  Let's do those
 * things next.  See ScenarioV2.
 */
public class ScenarioV1
{
    public static void main( String[] args ) {
        System.out.println("ScenarioV1");
        System.out.println("============================");

        final List<Hut> neighborhood = HutProviderV1.neighborhood();

        Print.showHuts(neighborhood);
    }
}
