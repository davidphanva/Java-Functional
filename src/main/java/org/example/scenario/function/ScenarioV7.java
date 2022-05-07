package org.example.scenario.function;

import org.example.data.HutProviderV1;
import org.example.model.Hut;
import org.example.util.Print;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In this scenario we want to display an address and the number of residents living in
 * at that address.
 */
public class ScenarioV7 {

    public static void main( String[] args ) {
        System.out.println("ScenarioV7");
        System.out.println("============================");

        final List<Hut> neighborhood = HutProviderV1.neighborhood();

        // First method - use objects created from static classes
        final Map<String, Integer> map1 = createAddressAndResidentCountMap(
                neighborhood,
                new AddressExtractorImpl(),
                new ResidentCountImpl());
        Print.showAddressAndResidentCount(map1);

        // Second method - use objects created via anonymous inner classes
        final Map<String, Integer> map2 = createAddressAndResidentCountMap(
                neighborhood,
                new AddressExtractorV1() {
                    @Override
                    public String extract(Hut hut) {
                        return hut.getAddress();
                    }
                },
                new ResidentCounterV1() {
                    @Override
                    public Integer count(Hut hut) {
                        return hut.getResidents().size();
                    }
                }
        );
        Print.showAddressAndResidentCount(map2);

        // Third method - Use lambda expressions
        final Map<String, Integer> map3 = createAddressAndResidentCountMap(
                neighborhood,
                hut -> hut.getAddress(),
                hut -> hut.getResidents().size()
        );
        Print.showAddressAndResidentCount(map3);
    }

    public static Map<String, Integer> createAddressAndResidentCountMap(
            List<Hut> huts,
            AddressExtractorV1 addressExtractor,
            ResidentCounterV1 residentCounter) {

        Map<String, Integer> map = new HashMap<>();

        for (Hut hut : huts) {

            map.put(addressExtractor.extract(hut), residentCounter.count(hut));
        }

        return map;
    }

    static class AddressExtractorImpl implements AddressExtractorV1 {

        @Override
        public String extract(Hut hut) {
            return hut.getAddress();
        }
    }

    static class ResidentCountImpl implements ResidentCounterV1 {

        @Override
        public Integer count(Hut hut) {
            return hut.getResidents().size();
        }
    }
    /**
     * So lambda expressions provide us a more concise way of doing the same thing
     * as static inner classes or anonymous inner classes.
     *
     * Looking at the ResidentCounterV1 and AddressExtractorV1 they both are given
     * an object and they return something.  Perhaps, we can abstract them a little
     * more.
     */
}
