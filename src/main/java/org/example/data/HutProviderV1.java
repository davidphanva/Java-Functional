package org.example.data;

import org.example.model.Color;
import org.example.model.Style;
import org.example.model.Hut;

import java.util.Arrays;
import java.util.List;

public class HutProviderV1 {

    private static final List<Hut> NEIGHBORHOOD1 = Arrays.asList(
            Hut.from("123 Comical Lane", Color.GREEN, Style.MODERN, Arrays.asList(
                    "Curly", "Larry", "Shemp"
            )),
            Hut.from("234 Forgotten Drive", Color.BLACK, Style.FUTURISTIC, Arrays.asList(
                    "Max", "Wax", "Flax"
            )),
            Hut.from("345 No Where Found Blvd", Color.WHITE, Style.HISTORIC, Arrays.asList(
                    "Papa Smurf", "Farmer Smurf", "Grouchy Smurf", "Handyman Smurf", "Jokey Smurf"
            )),
            Hut.from("456 Super Hero Court", Color.BLUE, Style.FUTURISTIC, Arrays.asList(
                    "Spiderman", "Black Widow", "Iceman", "Superman"
            )),
            Hut.from("567 Cartoon Circle", Color.GREEN, Style.MODERN, Arrays.asList(
                    "Scar", "Simba", "Rafiki", "Zazu", "Timon", "Nala", "Mufasa", "Shenzi"
            ))
    );

    public static List<Hut> neighborhood() {

        return NEIGHBORHOOD1;
    }
}
