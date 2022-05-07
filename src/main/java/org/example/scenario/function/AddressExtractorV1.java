package org.example.scenario.function;

import org.example.model.Hut;

@FunctionalInterface
public interface AddressExtractorV1 {

    String extract(Hut hut);
}
