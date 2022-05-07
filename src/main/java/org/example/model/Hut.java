package org.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hut {

    private String address;
    private Color color;
    private Style style;
    private State state;
    private boolean hasDeck = false;
    private List<String> residents;


    public static Hut from(String address) {

        return from(address, Color.YELLOW, Style.MODERN, Collections.EMPTY_LIST);
    }

    public static Hut from(String address, Style style) {

        return from(address, Color.GRAY, style, Collections.EMPTY_LIST);
    }

    public static Hut from(String address, Color color, Style style, List<String> residents) {

        Hut hut = new Hut();

        hut.address = address;
        hut.color = color;
        hut.style = style;
        hut.state = hut.style == Style.HISTORIC ? State.BROKEN : State.FUNCTIONAL;
        hut.residents = new ArrayList<>();

        if (residents != null)
            hut.residents.addAll(residents);

        return hut;
    }

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

    public void setColor(Color color) {
        this.color = color;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void addDeck() {
        this.hasDeck = true;
    }

    @Override
    public String toString() {
        return "Hut{" +
                "address='" + address + '\'' +
                ", color=" + color +
                ", style=" + style +
                ", state=" + state +
                ", hasDeck=" + hasDeck +
                ", residents=" + residents +
                '}';
    }

    private Hut() {}
}
