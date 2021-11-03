package com.codecool.dungeoncrawl.logic.actors.enemys;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.utils.Util;

import java.util.ArrayList;

public class Cyclops extends Enemy{
    public Cyclops(Cell cell) {
        super(cell);
        this.attack = 3;
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
        System.out.println(newcell.getNeighbor(basicposi[0],basicposi[1]).getTileName());
        if(newcell.getNeighbor(basicposi[0],basicposi[1]).getTileName() == "wall"){
            ArrayList<int[]> movements = new ArrayList<>();
            movements.add(new int[]{0,-1});
            movements.add(new int[]{-1,0});
            movements.add(new int[]{0,1});
            movements.add(new int[]{1,0});
            for (int i = 0; i < movements.size(); i++) {
                if (movements.get(i)[0] == basicposi[0] && movements.get(i)[1] == basicposi[1]){
                    movements.remove(i);
                }
            }
            return movements.get(Util.generateRandomBetween(0,2));
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
