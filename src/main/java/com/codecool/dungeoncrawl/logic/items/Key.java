package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Key extends Item{

    protected String color;

    public Key(Cell cell, String color) {
        super(cell);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String getTileName() {
        return String.format("%s key", color);
    }
}
