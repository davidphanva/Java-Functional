package org.example.data;

import org.example.model.Color;
import org.example.model.House;
import org.example.model.Style;

import java.util.Arrays;
import java.util.List;

public class HouseProviderV1 {

    private static final List<House> NEIGHBORHOOD1 = Arrays.asList(
            House.from("123 Comical Lane", Color.GREEN, Style.MODERN, Arrays.asList(
                    "Curly", "Larry", "Shemp"
            )),
            House.from("234 Forgotten Drive", Color.BLACK, Style.FUTURISTIC, Arrays.asList(
                    "Max", "Wax", "Flax"
            )),
            House.from("345 No Where Found Blvd", Color.WHITE, Style.HISTORIC, Arrays.asList(
                    "Papa Smurf", "Farmer Smurf", "Grouchy Smurf", "Handyman Smurf", "Jokey Smurf"
            )),
            House.from("456 Super Hero Court", Color.BLUE, Style.FUTURISTIC, Arrays.asList(
                    "Spiderman", "Black Widow", "Iceman", "Superman"
            )),
            House.from("567 Cartoon Circle", Color.GREEN, Style.MODERN, Arrays.asList(
                    "Scar", "Simba", "Rafiki", "Zazu", "Timon", "Nala", "Mufasa", "Shenzi"
            ))
    );

    public static List<House> neighborhood() {

        return NEIGHBORHOOD1;
    }
}
