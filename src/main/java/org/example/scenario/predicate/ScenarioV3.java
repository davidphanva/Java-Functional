package org.example.scenario.predicate;

import org.example.data.HutProviderV1;
import org.example.model.Style;
import org.example.model.Hut;
import org.example.util.Print;

import java.util.ArrayList;
import java.util.List;

/**
 * ScenarioV3 demonstrates searching for Hut objects by different criteria.
 * But the logic is pulled out of the search method and placed into an object.
 */
public class ScenarioV3 {

    public static void main( String[] args ) {
        System.out.println("ScenarioV3");
        System.out.println("============================");

        final List<Hut> neighborhood = HutProviderV1.neighborhood();

        final ResidentCountCriterion fourResidentCriterion = new ResidentCountCriterion(4);
        final List<Hut> fourResidents = searchByCriterion(neighborhood, fourResidentCriterion);
        Print.showHuts(fourResidents);

        final HomeStyleCriterion modernHomeCriterion = new HomeStyleCriterion(Style.MODERN);
        final List<Hut> modernHomes = searchByCriterion(neighborhood, modernHomeCriterion);
        Print.showHuts(modernHomes);

        final AddressCriterion stoogiesHomeCriterion = new AddressCriterion("123 Comical Lane");
        final List<Hut> stoogiesHome = searchByCriterion(neighborhood, stoogiesHomeCriterion);
        Print.showHuts(stoogiesHome);
    }

    static class ResidentCountCriterion implements HutSearchCriterion<Hut> {

        private int desiredCount;

        ResidentCountCriterion(int desiredCount) {

            this.desiredCount = desiredCount;
        }

        @Override
        public boolean matchesWith(Hut hut) {

            return hut.getResidents().size() == desiredCount;
        }
    }

    static class HomeStyleCriterion implements HutSearchCriterion<Hut> {

        private Style desiredStyle;

        HomeStyleCriterion(Style desiredStyle) {

            this.desiredStyle = desiredStyle;
        }

        @Override
        public boolean matchesWith(Hut hut) {

            return (hut.getStyle() == desiredStyle);
        }
    }

    static class AddressCriterion implements HutSearchCriterion<Hut> {

        private String desiredAddress;

        AddressCriterion(String desiredAddress) {

            this.desiredAddress = desiredAddress;
        }

        @Override
        public boolean matchesWith(Hut hut) {
            return (hut.getAddress().equalsIgnoreCase(desiredAddress));
        }
    }

    public static List<Hut> searchByCriterion(List<Hut> huts, HutSearchCriterion criteria) {

        List<Hut> found = new ArrayList<>();

        for (Hut hut : huts) {

            if (criteria.matchesWith(hut))
                found.add(hut);
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
