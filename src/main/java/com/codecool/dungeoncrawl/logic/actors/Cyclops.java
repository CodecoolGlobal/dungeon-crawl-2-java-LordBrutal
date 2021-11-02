package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Cyclops extends Actor{
    public Cyclops(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "cyclops";
    }
}
