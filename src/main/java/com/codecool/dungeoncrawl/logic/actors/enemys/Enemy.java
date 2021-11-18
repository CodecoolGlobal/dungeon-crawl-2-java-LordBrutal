package com.codecool.dungeoncrawl.logic.actors.enemys;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.utils.Util;

import java.util.ArrayList;
import java.util.Objects;

import static java.util.Arrays.asList;

public abstract class Enemy extends Actor {
    public Enemy(Cell cell) {
        super(cell);
        super.attack = 5;
    }

    public ArrayList<int[]> removeWalls(){
        Cell newCell = super.getCell();
        ArrayList<int[]> finalSteps = new ArrayList<>();
        ArrayList<int[]> movements = new ArrayList<>(asList(new int[]{0,-1}, new int[]{-1,0}, new int[]{0,1}, new int[]{1,0}));
        for (int[] movement : movements) {
            if (!(Objects.equals(newCell.getNeighbor(movement[0], movement[1]).getTileName(), "wall"))) {
                finalSteps.add(movement);
            }
        }
        return finalSteps;
    }

    @Override
    public String getTileName() {
        return null;
    }
}
