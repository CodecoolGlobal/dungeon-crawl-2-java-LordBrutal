package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class PickAxe extends Item{

    public PickAxe(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "pickaxe";
    }
}
