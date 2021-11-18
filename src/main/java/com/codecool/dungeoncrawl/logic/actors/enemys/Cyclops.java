package com.codecool.dungeoncrawl.logic.actors.enemys;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.utils.Util;

import java.util.ArrayList;

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

    private int[] dodgewall(){
        int[] basicposi = generatePosition();
        Cell newcell = super.getCell();
        if(newcell.getNeighbor(basicposi[0],basicposi[1]).getTileName() == "wall"){
            ArrayList<int[]> finalsteps = this.removWalls();
            return finalsteps.get(Util.generateRandomBetween(0,finalsteps.size()-1));
        }
        return basicposi;
    }

    public int[] nextStepCyclop(){
        return dodgewall();
    }



    public String getTileName() {
        return "cyclops";
    }
}
