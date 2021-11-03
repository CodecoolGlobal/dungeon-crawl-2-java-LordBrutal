package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

import java.util.Arrays;

public class Cyclops extends Actor{
    public Cyclops(Cell cell) {
        super(cell);
    }

    private int[] getPlayerPosition(){
        Cell cell = super.getCell();
        GameMap map = cell.getMap();
        int row = map.getPlayer().getY();
        int col = map.getPlayer().getX();
        return new int[]{row, col};
    }

    private int[] getCyclopsPosition(){
        Cell cell = super.getCell();
        GameMap map = cell.getMap();
        int row = map.getCyclops().getY();
        int col = map.getCyclops().getX();
        return new int[]{row, col};
    }

    private int[] generatePosition(){
        int[] playerPosition = getPlayerPosition();
        int[] cyclopsPosi = getCyclopsPosition();
        System.out.println(Arrays.toString(playerPosition));
        System.out.println(Arrays.toString(cyclopsPosi));
        if (playerPosition[1] < cyclopsPosi[1]){
            return new int[]{-1,0};
        }if (playerPosition[1] > cyclopsPosi[1]){
            return new int[]{1,0};
        }if (playerPosition[0] < cyclopsPosi[0]){
            return new int[]{0,-1};
        }
        return new int[]{0,1};
    }

    private int[] dodgewall(){
        int[] basicposi = generatePosition();
        return basicposi;
    }

    public int[] nextStepCyclop(){
        return dodgewall();
    }



    public String getTileName() {
        return "cyclops";
    }
}
