package org.example.sceanrio.function;

@FunctionalInterface
public interface DataExtractor<Input, Output> {

    // Here, House could be a type for Input and String could be a type for Output.
    // In another case, House could again be a type for Input and Integer could be
    // a type for Output.  See ScenarioV9 to DataExtractor usage.
    Output extract(Input input);
}
