package org.example.scenario.predicate;

import org.example.data.HouseProviderV1;
import org.example.model.HomeStyle;
import org.example.model.House;
import org.example.util.Print;

import java.util.ArrayList;
import java.util.List;

/**
 * ScenarioV2 demonstrates searching for House objects by different criteria.
 * Here, we're going to use procedural approach.
 */
public class ScenarioV2 {

    public static void main(String[] args) {
        System.out.println("ScenarioV2");
        System.out.println("============================");

        final List<House> neighborhood = HouseProviderV1.neighborhood();

        final List<House> fourResidents = byResidentCount(neighborhood, 4);
        Print.showNeighborhood(fourResidents);

        final List<House> modernHomes = byHomeStyle(neighborhood, HomeStyle.MODERN);
        Print.showNeighborhood(modernHomes);

        final List<House> stoogiesHome = byAddress(neighborhood, "123 Comical Lane");
        Print.showNeighborhood(stoogiesHome);
    }

    public static List<House> byResidentCount(List<House> houses, int desiredCount) {

        List<House> found = new ArrayList<>();

        for (House house : houses) {

            if (house.getResidents().size() == desiredCount) {

                found.add(house);
            }
        }

        return found;
    }

    public static List<House> byHomeStyle(List<House> houses, HomeStyle desiredHomeStyle) {

        List<House> found = new ArrayList<>();

        for (House house : houses) {

            if (house.getHomeStyle() == desiredHomeStyle) {

                found.add(house);
            }
        }

        return found;
    }

    public static List<House> byAddress(List<House> houses, String desiredAddress) {

        List<House> found = new ArrayList<>();

        for (House house : houses) {

            if (house.getAddress().equalsIgnoreCase(desiredAddress)) {

                found.add(house);
            }
        }

        return found;
    }

    /**
     * What if we need to search by other criteria.  Do we need to copy/paste these methods
     * and then change the code that enforce the criteria?
     *
     public static List<House> bySomeCriteria(List<House> houses, SomeCriteriaType criteria) {

     // Create a list to hold return value
     List<House> found = new ArrayList<>();

     // A general loop to go through each House object
     for (House house : houses) {

         // Here is the logic to enforce criteria.  This is where
         // the code differs.  What if we move this logic out of here
         // and into something else?  See ScenarioV3.
         if (house.getAddress().equalsIgnoreCase(desiredAddress)) {

            found.add(house);
         }
     // Stop enforcing criteria
     }

     return found;
     }
     */
}
