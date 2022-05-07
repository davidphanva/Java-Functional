package org.example.scenario.stream;

import org.example.data.HutProviderV1;
import org.example.model.Hut;

import java.util.List;
import java.util.stream.Collectors;

/**
 * In this scenario we'll focus on something simple that enables us understand
 * Java Stream better.
 */
public class ScenarioV16 {

    public static void main( String[] args ) {
        System.out.println("ScenarioV16");
        System.out.println("============================");

        final List<Hut> neighborhood = HutProviderV1.neighborhood();

        // The filter and map functions are simple as we're trying to understand more about Stream
        // and not the functions invoked by Stream.
        neighborhood.stream()
                .filter(hut -> true)
                .map(hut -> hut)
                .collect(Collectors.toList()); // We purposely discard the result List

        // What did we expect?
        // Did we expect all Hut objects to go through the filter process, then all of them go
        // through the map process?  Or did we expect Stream to pass a single Hut object through
        // the filter process, then the map process before processing another Hut object through
        // the filter process, and so on?

        // Let's try again with a couple of print statements.
        neighborhood.stream()
                .filter(hut -> {
                    System.out.println(
                            String.format("Filtering %s", hut.getAddress())
                    );
                    return true;
                })
                .map(hut -> {
                    System.out.println(
                            String.format("Mapping %s", hut.getAddress())
                    );
                    return hut;
                })
                .collect(Collectors.toList()); // We purposely discard the result List
    }

    /**
     * As we can see from the output, each Hut object goes through the filter, then the map steps, without having to
     * wait for another Hut object to finish the filter step.
     *
     * Should our application have a large dataset, we can actually see a result from the first Hut object (e.g.,
     * resident count) before the last Hut goes through the filter.  Using a loop for the filter step and another
     * loop for the map step, we cannot accomplish the same thing.
     *
     * But we can do the following, right?
     *
     * for () {
     *     filter(hut);
     *     map(hut);
     * }
     *
     * True, but how do we parallelize data processing when encountering large dataset?  Thread management is not
     * always trivial.  Let's see how Stream helps in ScenarioV17.
     */
}
