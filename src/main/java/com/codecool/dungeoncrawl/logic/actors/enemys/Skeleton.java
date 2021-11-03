package com.codecool.dungeoncrawl.logic.actors.enemys;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Enemy {


    public int getAttack() {
        return attack;
    }

    public Skeleton(Cell cell) {
        super(cell);
        this.attack = 2;
    }


    @Override
    public String getTileName() {
        return "skeleton";
    }
}
