package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Shield extends Item{

    private final int defenseModifier = 2;

    public Shield(Cell cell) {
        super(cell);
    }

    public int getDefenseModifier() {
        return defenseModifier;
    }

    @Override
    public String getTileName() {
        return "shield";
    }
}
