package org.example.scenario.function;

import org.example.model.House;

@FunctionalInterface
public interface ResidentCounterV1 {

    Integer count(House house);
}
