package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends Item{

    protected int attackModifier = 10;

    public Sword(Cell cell) {
        super(cell);
    }

    public int getAttackModifier() {
        return attackModifier;
    }

    @Override
    public String getTileName() {
        return "sword";
    }
}
