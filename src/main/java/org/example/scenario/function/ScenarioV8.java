package org.example.scenario.function;

import org.example.data.HutProviderV1;
import org.example.model.*;
import org.example.util.Print;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In ScenarioV8, we are going to make use of AddressExtractorV2 and
 * ResidentCountV2.  These have been generalized to use generics.
 */
public class ScenarioV8 {

    public static void main( String[] args ) {
        System.out.println("ScenarioV8");
        System.out.println("============================");

        final List<Hut> neighborhood = HutProviderV1.neighborhood();

        // First method - use objects created from static classes
        final Map<String, Integer> map1 = createAddressAndResidentCountMap(
                neighborhood,
                new AddressExtractorImpl(),
                new ResidentCountImpl());
        Print.showAddressAndResidentCount(map1);

        // Second method - use objects created via anonymous inner classes
        // Note: again, we need to specify types for AddressExtractorV2 and
        // ResidentCounterV2.
        final Map<String, Integer> map2 = createAddressAndResidentCountMap(
                neighborhood,
                new AddressExtractorV2<Hut, String>() {
                    @Override
                    public String extract(Hut hut) {
                        return hut.getAddress();
                    }
                },
                new ResidentCounterV2<Hut, Integer>() {
                    @Override
                    public Integer count(Hut hut) {
                        return hut.getResidents().size();
                    }
                }
        );
        Print.showAddressAndResidentCount(map2);

        // Third method - Use lambda expressions
        // Note: As for lambda expressions we don't even need to do anything.
        final Map<String, Integer> map3 = createAddressAndResidentCountMap(
                neighborhood,
                hut -> hut.getAddress(),
                hut -> hut.getResidents().size()
        );
        Print.showAddressAndResidentCount(map3);
    }

    // Note: We need to provide types to use AddressExtractorV2 and ResidentCounterV2
    public static Map<String, Integer> createAddressAndResidentCountMap(
            List<Hut> huts,
            AddressExtractorV2<Hut, String> addressExtractor,
            ResidentCounterV2<Hut, Integer> residentCounter) {

        Map<String, Integer> map = new HashMap<>();

        for (Hut hut : huts) {

            map.put(addressExtractor.extract(hut), residentCounter.count(hut));
        }

        return map;
    }

    // Note: Beside specifying Hut as the Input type and String as the Output
    // type, the implementation of the extract method does not change.
    static class AddressExtractorImpl implements AddressExtractorV2<Hut, String> {

        @Override
        public String extract(Hut hut) {
            return hut.getAddress();
        }
    }

    // Note: Similarly, Hut is the Input type and Integer is the Output
    // type as ResidentCounterV2 requires.  The implementation of the count
    // method does not change.
    static class ResidentCountImpl implements ResidentCounterV2<Hut, Integer> {

        @Override
        public Integer count(Hut hut) {
            return hut.getResidents().size();
        }
    }
    /**
     * But let's compare AddressExtractorV2 and ResidentCounterV2.  They look so similar.
     * In fact, we can combine them.  See DataExtractor and ScenarioV9.
     */
}
