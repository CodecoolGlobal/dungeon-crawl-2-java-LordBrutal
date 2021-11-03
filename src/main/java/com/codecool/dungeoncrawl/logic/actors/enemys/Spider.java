package com.codecool.dungeoncrawl.logic.actors.enemys;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.utils.Util;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class Spider extends Enemy{
    private ArrayList<int[]> movements = new ArrayList<>();

    public Spider(Cell cell) {
        super(cell);
        this.attack = 5;
        movements.add(new int[]{0,-1});
        movements.add(new int[]{-1,0});
        movements.add(new int[]{0,1});
        movements.add(new int[]{1,0});
    }

    public int[] nextStep(){
        ArrayList<int[]> finalsteps = this.removWalls();
        return finalsteps.get(Util.generateRandomBetween(0,finalsteps.size()-1));
    }


    @Override
    public String getTileName() {
        return "spider";
    }
}
