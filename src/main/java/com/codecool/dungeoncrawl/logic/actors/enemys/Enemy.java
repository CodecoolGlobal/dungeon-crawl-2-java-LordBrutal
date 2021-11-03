package com.codecool.dungeoncrawl.logic.actors.enemys;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public abstract class Enemy extends Actor {
    public Enemy(Cell cell) {
        super(cell);
        super.attack = 5;
    }

    @Override
    public String getTileName() {
        return null;
    }
}
