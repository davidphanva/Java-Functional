package org.example.sceanrio.function;

import org.example.data.HouseProviderV1;
import org.example.model.House;
import org.example.util.Print;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * In ScenarioV11, we will see how the compose and the andThen methods from
 * the java.util.function.Function interface can be used.
 *
 * Let's consider this example.  In some part of the World, people believe
 * that if one adds up all digits of an address and the closer the sum is
 * to 9 the more lucky that house is.  Can we compute the sum of each
 * house address using the Function interface?
 *
 * We know we can use Function to extract a String object which represents
 * the address of a House object.  We can provide that String to another
 * Function which also returns a String, but this would only contain house
 * number.  Then this string can be an input of another Function, which
 * computes and returns the sum.
 */
public class ScenarioV11 {

    public static void main( String[] args ) {
        System.out.println("ScenarioV11");
        System.out.println("============================");

        final List<House> neighborhood = HouseProviderV1.neighborhood();

        // Here we strictly use lambda expressions
        final Map<String, Integer> map = computeAddressSum(
                neighborhood,
                house -> house.getAddress(),
                address -> address.substring(0, address.indexOf(' ')),
                streetNumber -> {
                    char[] charArray = streetNumber.toCharArray();
                    int sum = 0;
                    for (char c : charArray) {
                        sum += Character.getNumericValue(c);
                        if (sum > 9)
                            sum %= 9;
                    }

                    return sum;
                }
        );
        Print.showAddressAndSumOfHouseNumber(map);
    }

    public static Map<String, Integer> computeAddressSum(
            List<House> houses,
            Function<House, String> addressExtractor,
            Function<String, String> houseNumberExtractor,
            Function<String, Integer> sumExtractor) {

        Map<String, Integer> map = new HashMap<>();

        for (House house : houses) {

            Integer sum = houseNumberExtractor.compose(addressExtractor).andThen(sumExtractor).apply(house);
            map.put(addressExtractor.apply(house), sum);
        }

        return map;
    }


    /**
     * The computeAddressSum chains together 3 functions.  The first function takes an object and produces
     * an output.  The second function accepts that output as its input then produces its own output.  Then
     * this pattern repeats.  Essentially, this is the Adapter Pattern in object oriented design.
     *
     * We have a choice to chain together these functions inside or outside of the computeAddressSum method.
     * It's probably better to do it outside.
     *
     * The Function interface is for those functions that accepts a single object as input and produces another
     * object.  What if we need to have a function that accepts 2 objects as its parameters?  Java 8 has the
     * BiFunction interface for this.  See if you can come up with an example of how to use it.
     */
}
