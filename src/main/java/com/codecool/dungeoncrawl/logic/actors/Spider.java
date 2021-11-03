package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.utils.Util;

import java.util.ArrayList;

public class Spider extends Actor{
    private ArrayList<int[]> movements = new ArrayList<>();

    public Spider(Cell cell) {
        super(cell);
        movements.add(new int[]{0,-1});
        movements.add(new int[]{-1,0});
        movements.add(new int[]{0,1});
        movements.add(new int[]{1,0});
    }

    public int[] nextStep(){
        return movements.get(Util.generateRandomBetween(0,3));
    }


    @Override
    public String getTileName() {
        return "spider";
    }
}
