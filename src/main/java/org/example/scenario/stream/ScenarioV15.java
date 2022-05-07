package org.example.scenario.stream;

import org.example.data.HutProviderV1;
import org.example.model.Color;
import org.example.model.Style;
import org.example.model.Hut;
import org.example.util.Print;

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

        final List<Hut> neighborhood = HutProviderV1.neighborhood();

        Print.showHuts(neighborhood);

        // Here we're going to use some of the Stream capabilities
        final Stream<Hut> HUT_STREAM = neighborhood.stream();

        final Predicate<Hut> HISTORIC_HOMES = hut -> hut.getStyle() == Style.HISTORIC;
        final Predicate<Hut> CROWDED_HOMES = hut -> hut.getResidents().size() > 3;
        final Predicate<Hut> GOVT_CRITERIA = HISTORIC_HOMES.or(CROWDED_HOMES);

        final Random RANDOM = new Random();
        final Supplier<Integer> HUT_NUMBER_GENERATOR = () -> RANDOM.nextInt(100);

        final Function<String, Hut> HUD = (residentName) -> {
            return Hut.from(
                    String.format("%d New Village Parkway", HUT_NUMBER_GENERATOR.get()),
                    Color.BLUE,
                    Style.MODERN,
                    Arrays.asList(residentName));
        };

        final Function<Hut, List<Hut>> GOVT_PROGRAM = hut -> {

            List<Hut> village = hut.getResidents().stream()
                    .map(HUD)
                    .collect(Collectors.toList());

            return village;
        };

        List<List<Hut>> villages = HUT_STREAM
                .filter(GOVT_CRITERIA)
                .map(GOVT_PROGRAM)
                .collect(Collectors.toList());

        Print.showHutVillages(villages);
    }

    /**
     * Why did we use Stream?  For loops work!
     *
     * Our dataset is limited.  It starts out with 5 Hut objects.  What if our dataset is large (~1_000's of
     * objects or maybe infinite).  If we have a couple of loops to iterate over a large dataset, it'll take
     * a long time before we see a result from the first object in the dataset.  If the dataset is infinite
     * the second loop will never start.  Can we even parallelize the processing of data with loops?  We can
     * iterate and pass each object to a thread.  But now we need to deal with thread management.  Our application
     * becomes more complex to maintain.
     */
}
