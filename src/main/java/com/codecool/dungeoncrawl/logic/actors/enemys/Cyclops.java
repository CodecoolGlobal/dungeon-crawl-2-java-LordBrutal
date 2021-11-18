package com.codecool.dungeoncrawl.logic.actors.enemys;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.utils.Util;

import java.util.ArrayList;
import java.util.Objects;

public class Cyclops extends Enemy{
    public Cyclops(Cell cell) {
        super(cell);
        this.attack = 10;
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
        if (playerPosition[1] < cyclopsPosi[1]){
            return new int[]{-1,0};
        }if (playerPosition[1] > cyclopsPosi[1]){
            return new int[]{1,0};
        }if (playerPosition[0] < cyclopsPosi[0]){
            return new int[]{0,-1};
        }
        return new int[]{0,1};
    }

    private int[] dodgeWall(){
        int[] basicPosition = generatePosition();
        Cell newCell = super.getCell();
        if(Objects.equals(newCell.getNeighbor(basicPosition[0], basicPosition[1]).getTileName(), "wall")){
            ArrayList<int[]> finalSteps = this.removWalls();
            return finalSteps.get(Util.generateRandomBetween(0,finalSteps.size()-1));
        }
        return basicPosition;
    }

    public int[] nextStepCyclops(){
        return dodgeWall();
    }



    public String getTileName() {
        return "cyclops";
    }
}
