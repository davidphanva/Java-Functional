package org.example.util;

import org.example.model.House;

import java.util.List;
import java.util.Map;

public class Print {

    public static void showNeighborhood(List<House> neighborhood) {

        for (House house : neighborhood) {

            System.out.println(house);
        }

        System.out.println("---------------------------");
    }

    public static void showAddressAndResidentCount(Map<String, Integer> map) {

        for (Map.Entry<String, Integer> entry : map.entrySet()) {

            System.out.println(
                    String.format("Number of residents living at %s: %d",
                            entry.getKey(),
                            entry.getValue()));
        }

        System.out.println("---------------------------");
    }

    public static void showAddressAndSumOfHouseNumber(Map<String, Integer> map) {

        for (Map.Entry<String, Integer> entry : map.entrySet()) {

            System.out.println(
                    String.format("The address '%s' has a sum of %d",
                            entry.getKey(),
                            entry.getValue()));
        }

        System.out.println("---------------------------");
    }
}
