package org.example.sceanrio.stream;

import org.example.data.HouseProviderV1;
import org.example.model.Color;
import org.example.model.HomeStyle;
import org.example.model.House;
import org.example.util.Print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Let's say a local government wants to achieve a couple of things:
 *
 * 1. Preserve historic homes; and therefore, residents who currently live in
 * those homes need to move out.  In order to not upset these residents, the
 * government will provide a modern home for each individual.
 *
 * 2. Promote the real estate industry by "encouraging" people to live in
 * individual homes.  Those existing homes with more than 3 residents qualify to
 * receive the incentive, such that they can have their own individual homes.
 */
public class ScenarioV15 {

    public static void main( String[] args ) {
        System.out.println("ScenarioV15");
        System.out.println("============================");

        final List<House> neighborhood = HouseProviderV1.neighborhood();

        Print.showNeighborhood(neighborhood);

        // Here we're going to use some of the Stream capabilities
        final Stream<House> HOUSE_STREAM = neighborhood.stream();

        final Predicate<House> HISTORIC_HOMES = house -> house.getHomeStyle() == HomeStyle.HISTORIC;
        final Predicate<House> CROWDED_HOMES = house -> house.getResidents().size() > 3;
        final Predicate<House> GOVT_CRITERIA = HISTORIC_HOMES.or(CROWDED_HOMES);

        final Random RANDOM = new Random();
        final Supplier<Integer> HOUSE_NUMBER_GENERATOR = () -> RANDOM.nextInt(100);

        final Function<String, House> HUD = (residentName) -> {
            return House.from(
                    String.format("%d New Village Parkway", HOUSE_NUMBER_GENERATOR.get()),
                    Color.BLUE,
                    HomeStyle.MODERN,
                    Arrays.asList(residentName));
        };

        final Function<House, List<House>> GOVT_PROGRAM = house -> {

            List<House> village = house.getResidents().stream()
                    .map(HUD)
                    .collect(Collectors.toList());

            return village;
        };

        List<List<House>> villages = HOUSE_STREAM
                .filter(GOVT_CRITERIA)
                .map(GOVT_PROGRAM)
                .collect(Collectors.toList());

        Print.showVillages(villages);
    }

    /**
     * Why did we use Stream?  For loops work!
     *
     * Our dataset is limited.  It starts out with 5 House objects.  What if our dataset is large (~1_000's of
     * objects or maybe infinite).  If we have a couple of loops to iterate over a large dataset, it'll take
     * a long time before we see a result from the first object in the dataset.  If the dataset is infinite
     * the second loop will never start.  Can we even parallelize the processing of data with loops?  We can
     * iterate and pass each object to a thread.  But now we need to deal with thread management.  Our application
     * becomes more complex to maintain.
     */
}
