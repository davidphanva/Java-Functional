package org.example.scenario.function;

import org.example.data.HouseProviderV1;
import org.example.model.House;
import org.example.util.Print;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * In ScenarioV10, we are going to use java.util.function.Function instead of
 * DataExtractor.
 */
public class ScenarioV10 {

    public static void main( String[] args ) {
        System.out.println("ScenarioV10");
        System.out.println("============================");

        final List<House> neighborhood = HouseProviderV1.neighborhood();

        // First method - use objects created from static classes
        final Map<String, Integer> map1 = createAddressAndResidentCountMap(
                neighborhood,
                new AddressExtractorImpl(),
                new ResidentCountImpl());
        Print.showAddressAndResidentCount(map1);

        // Second method - use objects created via anonymous inner classes
        // Note: again, we need to specify types for DataExtractor.
        final Map<String, Integer> map2 = createAddressAndResidentCountMap(
                neighborhood,
                new Function<House, String>() {
                    @Override
                    public String apply(House house) {
                        return house.getAddress();
                    }
                },
                new Function<House, Integer>() {
                    @Override
                    public Integer apply(House house) {
                        return house.getResidents().size();
                    }
                }
        );
        Print.showAddressAndResidentCount(map2);

        // Third method - Use lambda expressions
        // Note: As for lambda expressions we don't even need to do anything.
        final Map<String, Integer> map3 = createAddressAndResidentCountMap(
                neighborhood,
                house -> house.getAddress(),
                house -> house.getResidents().size()
        );
        Print.showAddressAndResidentCount(map3);
    }

    // Note: We need to provide types to use Function
    public static Map<String, Integer> createAddressAndResidentCountMap(
            List<House> houses,
            Function<House, String> addressExtractor,
            Function<House, Integer> residentCounter) {

        Map<String, Integer> map = new HashMap<>();

        for (House house : houses) {

            map.put(addressExtractor.apply(house), residentCounter.apply(house));
        }

        return map;
    }

    // Note: We still need to specify House and String as the Input and Output
    // types.  And now we implement the apply method instead of the extract method.
    static class AddressExtractorImpl implements Function<House, String> {

        @Override
        public String apply(House house) {
            return house.getAddress();
        }
    }

    // Note: Similarly, House is the Input type and Integer is the Output
    // type as Function requires.
    static class ResidentCountImpl implements Function<House, Integer> {

        @Override
        public Integer apply(House house) {
            return house.getResidents().size();
        }
    }

    /**
     * In summary, lambda expressions are powerful and concise.  They take a little bit
     * of effort to get used to.  But they require no changes as we saw in the last
     * few scenarios.  And because Java 8 provides interfaces such as Predicate and
     * Function, we can use them and write even less code.  Yay!
     *
     * Is that all the Function interface can do?  This interface has other
     * default methods such as compose and andThen.  How do we use these?
     * See ScenarioV11.
     */
}
