package org.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class House {

    private String address;
    private Color color;
    private HomeStyle homeStyle;
    private State state;
    private boolean hasDeck = false;
    private List<String> residents;


    public static House from(String address) {

        return from(address, Color.YELLOW, HomeStyle.MODERN, Collections.EMPTY_LIST);
    }

    public static House from(String address, HomeStyle homeStyle) {

        return from(address, Color.GRAY, homeStyle, Collections.EMPTY_LIST);
    }

    public static House from(String address, Color color, HomeStyle homeStyle, List<String> residents) {

        House house = new House();

        house.address = address;
        house.color = color;
        house.homeStyle = homeStyle;
        house.state = house.homeStyle == HomeStyle.HISTORIC ? State.BROKEN : State.FUNCTIONAL;
        house.residents = new ArrayList<>();

        if (residents != null)
            house.residents.addAll(residents);

        return house;
    }

    public String getAddress() {
        return address;
    }

    public List<String> getResidents() {
        return residents;
    }

    public HomeStyle getHomeStyle() {
        return homeStyle;
    }

    public State getState() {
        return state;
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
        return "House{" +
                "address='" + address + '\'' +
                ", color=" + color +
                ", homeStyle=" + homeStyle +
                ", state=" + state +
                ", hasDeck=" + hasDeck +
                ", residents=" + residents +
                '}';
    }

    private House() {}
}
