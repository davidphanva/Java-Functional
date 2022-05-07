package org.example.scenario.predicate;

import org.example.data.HutProviderV1;
import org.example.model.Style;
import org.example.model.Hut;
import org.example.util.Print;

import java.util.ArrayList;
import java.util.List;

/**
 * ScenarioV2 demonstrates searching for Hut objects by different criteria.
 * Here, we're going to use procedural approach.
 */
public class ScenarioV2 {

    public static void main(String[] args) {
        System.out.println("ScenarioV2");
        System.out.println("============================");

        final List<Hut> neighborhood = HutProviderV1.neighborhood();

        final List<Hut> fourResidents = byResidentCount(neighborhood, 4);
        Print.showHuts(fourResidents);

        final List<Hut> modernHomes = byHomeStyle(neighborhood, Style.MODERN);
        Print.showHuts(modernHomes);

        final List<Hut> stoogiesHome = byAddress(neighborhood, "123 Comical Lane");
        Print.showHuts(stoogiesHome);
    }

    public static List<Hut> byResidentCount(List<Hut> huts, int desiredCount) {

        List<Hut> found = new ArrayList<>();

        for (Hut hut : huts) {

            if (hut.getResidents().size() == desiredCount) {

                found.add(hut);
            }
        }

        return found;
    }

    public static List<Hut> byHomeStyle(List<Hut> huts, Style desiredStyle) {

        List<Hut> found = new ArrayList<>();

        for (Hut hut : huts) {

            if (hut.getStyle() == desiredStyle) {

                found.add(hut);
            }
        }

        return found;
    }

    public static List<Hut> byAddress(List<Hut> huts, String desiredAddress) {

        List<Hut> found = new ArrayList<>();

        for (Hut hut : huts) {

            if (hut.getAddress().equalsIgnoreCase(desiredAddress)) {

                found.add(hut);
            }
        }

        return found;
    }

    /**
     * What if we need to search by other criteria.  Do we need to copy/paste these methods
     * and then change the code that enforce the criteria?
     *
     public static List<Hut> bySomeCriteria(List<Hut> huts, SomeCriteriaType criteria) {

     // Create a list to hold return value
     List<Hut> found = new ArrayList<>();

     // A general loop to go through each Hut object
     for (Hut hut : huts) {

         // Here is the logic to enforce criteria.  This is where
         // the code differs.  What if we move this logic out of here
         // and into something else?  See ScenarioV3.
         if (hut.getAddress().equalsIgnoreCase(desiredAddress)) {

            found.add(hut);
         }
     // Stop enforcing criteria
     }

     return found;
     }
     */
}
