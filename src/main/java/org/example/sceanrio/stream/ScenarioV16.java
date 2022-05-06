package org.example.sceanrio.stream;

import org.example.data.HouseProviderV1;
import org.example.model.Color;
import org.example.model.HomeStyle;
import org.example.model.House;
import org.example.util.Print;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * In this scenario we'll focus on something simple that enables us understand
 * Java Stream better.
 */
public class ScenarioV16 {

    public static void main( String[] args ) {
        System.out.println("ScenarioV16");
        System.out.println("============================");

        final List<House> neighborhood = HouseProviderV1.neighborhood();

        // The filter and map functions are simple as we're trying to understand more about Stream
        // and not the functions invoked by Stream.
        neighborhood.stream()
                .filter(house -> true)
                .map(house -> house)
                .collect(Collectors.toList()); // We purposely discard the result List

        // What did we expect?
        // Did we expect all House objects to go through the filter process, then all of them go
        // through the map process?  Or did we expect Stream to pass a single House object through
        // the filter process, then the map process before processing another House object through
        // the filter process, and so on?

        // Let's try again with a couple of print statements.
        neighborhood.stream()
                .filter(house -> {
                    System.out.println(
                            String.format("Filtering %s", house.getAddress())
                    );
                    return true;
                })
                .map(house -> {
                    System.out.println(
                            String.format("Mapping %s", house.getAddress())
                    );
                    return house;
                })
                .collect(Collectors.toList()); // We purposely discard the result List
    }

    /**
     * As we can see from the output, each House object goes through the filter, then the map steps, without having to
     * wait for another House object to finish the filter step.
     *
     * Should our application have a large dataset, we can actually see a result from the first House object (e.g.,
     * resident count) before the last House goes through the filter.  Using a loop for the filter step and another
     * loop for the map step, we cannot accomplish the same thing.
     *
     * But we can do the following, right?
     *
     * for () {
     *     filter(house);
     *     map(house);
     * }
     *
     * True, but how do we parallelize data processing when encountering large dataset?  Thread management is not
     * always trivial.  Let's see how Stream helps in ScenarioV17.
     */
}
