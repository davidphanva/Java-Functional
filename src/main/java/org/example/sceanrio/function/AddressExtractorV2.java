package org.example.sceanrio.function;

@FunctionalInterface
public interface AddressExtractorV2<Input, Output> {

    // Here, House could be a type for Input and String could be a type for Output
    Output extract(Input input);
}
