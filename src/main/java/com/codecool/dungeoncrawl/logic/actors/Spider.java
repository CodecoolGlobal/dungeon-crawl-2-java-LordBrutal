package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.ArrayList;

public class Spider extends Actor{
    int index = 0;
    private ArrayList<int[]> movements = new ArrayList<>();

    public Spider(Cell cell) {
        super(cell);
        movements.add(new int[]{0,-1});
        movements.add(new int[]{-1,0});
        movements.add(new int[]{0,1});
        movements.add(new int[]{1,0});

    }

    public int[] nextStep(){
        int[] nextStep = movements.get(index);
        index ++;
        if (index == movements.size()){
            index = 0;
        }
        return nextStep;
    }


    @Override
    public String getTileName() {
        return "spider";
    }
}
