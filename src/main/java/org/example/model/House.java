package org.example.model;

import java.util.Collections;
import java.util.List;

public final class House {

    private final String address;
    private final Color color;
    private final Style style;
    private final State state;
    private final boolean hasDeck;
    private final List<String> residents;


    // static factory method
    public static House from(String address) {

        return from(address, Color.YELLOW, Style.MODERN, Collections.EMPTY_LIST);
    }

    // static factory method
    public static House from(String address, Style style) {

        return from(address, Color.GRAY, style, Collections.EMPTY_LIST);
    }

    // static factory method
    public static House from(String address, Color color, Style style, List<String> residents) {

        House hut = new House(address,
                color,
                style,
                style == Style.HISTORIC ? State.BROKEN : State.FUNCTIONAL,
                false,
                residents);

        return hut;
    }

    // static factory method
    public static House from(String address,
                             Color color,
                             Style style,
                             State state,
                             boolean hasDeck,
                             List<String> residents) {

        House hut = new House(address,
                color,
                style,
                state,
                hasDeck,
                residents);

        return hut;
    }

    // Getters are provided
    public String getAddress() {
        return address;
    }

    public List<String> getResidents() {
        return residents;
    }

    public Style getStyle() {
        return style;
    }

    public State getState() {
        return state;
    }

    public Color getColor() {
        return color;
    }

    // Immutable classes should not provide setters, as we don't want to provide
    // a means to change objects.  But sometimes, changes to objects are
    // necessary.  One way to deal with this is to provide a new object that
    // contains the desired changes.  Doing so enables us to adhere to the
    // immutability paradigm.
    public House setColor(Color color) {

        return new House(this.address,
                color,
                this.style,
                this.state,
                this.hasDeck,
                this.residents);
    }

    public House setState(State state) {

        return new House(this.address,
                this.color,
                this.style,
                state,
                this.hasDeck,
                this.residents);
    }

    public House addDeck() {

        return new House(this.address,
                this.color,
                this.style,
                this.state,
                this.hasDeck,
                this.residents);
    }

    @Override
    public String toString() {
        return "House{" +
                "address='" + address + '\'' +
                ", color=" + color +
                ", style=" + style +
                ", state=" + state +
                ", hasDeck=" + hasDeck +
                ", residents=" + residents +
                '}';
    }

    // Constructor is kept private but static factory methods are provided to create objects.
    // This enables the separation of how objects are created from how/where they are used.
    private House(String address, Color color, Style style, State state, boolean hasDeck, List<String> residents) {

        this.address = address;
        this.color = color;
        this.style = style;
        this.state = state;
        this.hasDeck = hasDeck;
        this.residents = residents;
    }
}
