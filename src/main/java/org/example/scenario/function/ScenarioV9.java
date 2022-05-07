package org.example.scenario.function;

import org.example.data.HutProviderV1;
import org.example.model.Hut;
import org.example.util.Print;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In ScenarioV9, we are going to use DataExtractor instead of
 * AddressExtractorV2 and ResidentCounterV2.
 */
public class ScenarioV9 {

    public static void main( String[] args ) {
        System.out.println("ScenarioV9");
        System.out.println("============================");

        final List<Hut> neighborhood = HutProviderV1.neighborhood();

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
                new DataExtractor<Hut, String>() {
                    @Override
                    public String extract(Hut hut) {
                        return hut.getAddress();
                    }
                },
                new DataExtractor<Hut, Integer>() {
                    @Override
                    public Integer extract(Hut hut) {
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

    // Note: We need to provide types to use DataExtractor
    public static Map<String, Integer> createAddressAndResidentCountMap(
            List<Hut> huts,
            DataExtractor<Hut, String> addressExtractor,
            DataExtractor<Hut, Integer> residentCounter) {

        Map<String, Integer> map = new HashMap<>();

        for (Hut hut : huts) {

            map.put(addressExtractor.extract(hut), residentCounter.extract(hut));
        }

        return map;
    }

    // Note: Beside specifying Hut as the Input type and String as the Output
    // type, the implementation of the extract method does not change.
    // Also this class implements DataExtractor instead of AddressExtractorV2.
    static class AddressExtractorImpl implements DataExtractor<Hut, String> {

        @Override
        public String extract(Hut hut) {
            return hut.getAddress();
        }
    }

    // Note: Similarly, Hut is the Input type and Integer is the Output
    // type as DataExtractor requires.  Now, this class implements
    // the extract method instead of the count method.  But the internal
    // implementation does not change.
    static class ResidentCountImpl implements DataExtractor<Hut, Integer> {

        @Override
        public Integer extract(Hut hut) {
            return hut.getResidents().size();
        }
    }
    /**
     * So what does the DataExtractor interface do?  It's just a way to specify
     * a function, which takes a type as input and returns a different type.  All the
     * logic of such functions would be wrapped in the extract method.
     *
     * But if we think about it, there are a lot of functions that take an input
     * and returns something else as output.  Their implementations may be different
     * but their signatures are the same from generics standpoint.
     *
     * In fact Java 8 already provides an interface that is similar to DataExtractor,
     * but in a more general way.  It's the jav.util.Function interface.  Let's use this
     * in ScenarioV10 so we don't even have to define DataExtractor.
     */
}
