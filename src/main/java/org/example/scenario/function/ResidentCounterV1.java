package org.example.scenario.function;

import org.example.model.Hut;

@FunctionalInterface
public interface ResidentCounterV1 {

    Integer count(Hut hut);
}
