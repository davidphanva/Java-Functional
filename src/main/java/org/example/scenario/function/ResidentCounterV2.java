package org.example.scenario.function;

@FunctionalInterface
public interface ResidentCounterV2<Input, Output> {

    // Here, House could be a type for Input and Integer could be a type for Output
    Output count(Input input);
}
