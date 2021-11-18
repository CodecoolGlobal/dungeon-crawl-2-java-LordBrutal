package com.codecool.dungeoncrawl.logic.actors.enemys;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.utils.Util;

import java.util.ArrayList;



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
        ArrayList<int[]> finalSteps = this.removeWalls();
        return finalSteps.get(Util.generateRandomBetween(0,finalSteps.size()-1));
    }


    @Override
    public String getTileName() {
        return "spider";
    }
}
