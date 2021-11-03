package com.codecool.dungeoncrawl.logic;

public enum Color {
    BLUE("blue"),
    RED("red"),
    GOLD("gold");

    private final String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
