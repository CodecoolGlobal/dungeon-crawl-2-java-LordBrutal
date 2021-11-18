package com.codecool.dungeoncrawl.logic.actors.enemys;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public abstract class Enemy extends Actor {
    public Enemy(Cell cell) {
        super(cell);
        super.attack = 5;
    }

    public ArrayList<int[]> removWalls(){
        Cell newCell = super.getCell();
        ArrayList<int[]> finalsteps = new ArrayList<>();
        ArrayList<int[]> movements = new ArrayList<>(asList(new int[]{0,-1}, new int[]{-1,0}, new int[]{0,1}, new int[]{1,0}));
        for (int i = 0; i < movements.size(); i++) {
            if (!(newCell.getNeighbor(movements.get(i)[0], movements.get(i)[1]).getTileName() == "wall")) {
                finalsteps.add(movements.get(i));
            }
        }
        return finalsteps;
    }

    @Override
    public String getTileName() {
        return null;
    }
}
