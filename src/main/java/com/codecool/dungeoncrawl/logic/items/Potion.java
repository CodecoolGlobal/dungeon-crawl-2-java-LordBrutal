package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Potion extends Item{

    private int healthIncrease = 25;

    public Potion(Cell cell) {
        super(cell);
    }

    public int getHealthIncrease() {
        return healthIncrease;
    }

    @Override
    public String getTileName() {
        return "potion";
    }
}
