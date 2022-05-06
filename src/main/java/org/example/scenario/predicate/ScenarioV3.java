package org.example.scenario.predicate;

import org.example.data.HouseProviderV1;
import org.example.model.HomeStyle;
import org.example.model.House;
import org.example.util.Print;

import java.util.ArrayList;
import java.util.List;

/**
 * ScenarioV3 demonstrates searching for House objects by different criteria.
 * But the logic is pulled out of the search method and placed into an object.
 */
public class ScenarioV3 {

    public static void main( String[] args ) {
        System.out.println("ScenarioV3");
        System.out.println("============================");

        final List<House> neighborhood = HouseProviderV1.neighborhood();

        final ResidentCountCriterion fourResidentCriterion = new ResidentCountCriterion(4);
        final List<House> fourResidents = searchByCriterion(neighborhood, fourResidentCriterion);
        Print.showNeighborhood(fourResidents);

        final HomeStyleCriterion modernHomeCriterion = new HomeStyleCriterion(HomeStyle.MODERN);
        final List<House> modernHomes = searchByCriterion(neighborhood, modernHomeCriterion);
        Print.showNeighborhood(modernHomes);

        final AddressCriterion stoogiesHomeCriterion = new AddressCriterion("123 Comical Lane");
        final List<House> stoogiesHome = searchByCriterion(neighborhood, stoogiesHomeCriterion);
        Print.showNeighborhood(stoogiesHome);
    }

    static class ResidentCountCriterion implements HouseSearchCriterion<House> {

        private int desiredCount;

        ResidentCountCriterion(int desiredCount) {

            this.desiredCount = desiredCount;
        }

        @Override
        public boolean matchesWith(House house) {

            return house.getResidents().size() == desiredCount;
        }
    }

    static class HomeStyleCriterion implements HouseSearchCriterion<House> {

        private HomeStyle desiredHomeStyle;

        HomeStyleCriterion(HomeStyle desiredHomeStyle) {

            this.desiredHomeStyle = desiredHomeStyle;
        }

        @Override
        public boolean matchesWith(House house) {

            return (house.getHomeStyle() == desiredHomeStyle);
        }
    }

    static class AddressCriterion implements HouseSearchCriterion<House> {

        private String desiredAddress;

        AddressCriterion(String desiredAddress) {

            this.desiredAddress = desiredAddress;
        }

        @Override
        public boolean matchesWith(House house) {
            return (house.getAddress().equalsIgnoreCase(desiredAddress));
        }
    }

    public static List<House> searchByCriterion(List<House> houses, HouseSearchCriterion criteria) {

        List<House> found = new ArrayList<>();

        for (House house : houses) {

            if (criteria.matchesWith(house))
                found.add(house);
        }

        return found;
    }

    /**
     * While the search method (i.e., searchByCriterion) is now more simple.  But
     * additional classes (e.g., AddressCriterion, HomeStyleCriterion) are needed.
     * So we essentially shifted solving this search problem from procedural approach
     * to an object-oriented one by encapsulating decisions in objects.
     * Is there a better way to reduce the amount of code?
     */
}
