package org.example.sceanrio.function;

import org.example.model.House;

@FunctionalInterface
public interface AddressExtractorV1 {

    String extract(House house);
}
