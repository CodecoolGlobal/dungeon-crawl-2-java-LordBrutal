package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Player extends Actor {
    private String name;
    public Player(Cell cell) {
        super(cell);
        this.attack = 5;
        this.name = "player";
    }


    public int getAttack() {
        return attack;
    }

    public String getTileName() {
        return this.name;
    }

    public void setTileName(String newName) {
        this.name = newName;
    }

}
