package com.codecool.dungeoncrawl.logic.create_map_components;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;

public class BreakableWalls {

    private static int[][] level1Walls = new int[][]
            {{23,2}, {24,2}, {25,2}, {26,2}, {27,2}, {28,2}, {29,2}, {30,2}, {31,2}, {32,2},
                    {33,2}, {34,2}, {35,2}, {36,2}, {37,2}, {38,2}, {39,2}, {39,3}, {39,4}, {39,5},
                    {5,6}, {6,6}, {7,6}, {8,6}, {9,6}, {10, 6}, {11,6}, {12,6}, {13,6}, {14,6},
                    {15,6}, {16,6}, {39,6}, {39,7}, {39,8}, {39,9}, {39,10}, {39,12}, {39,13}, {39,14},
                    {39,15}, {39,16}, {39,17}, {39,18}, {7,19}};

    private static int[][] level2Walls = new int[][]
            {{2, 2}, {11,2}, {38,12}, {40,19}, {37,23}, {35,28}};

    public static void loadMapWalls(GameMap map) {
        int[][] walls = new int[0][];
        if(map.getLevel() == 1) {
            walls = level1Walls;
//            int[][] level1Walls = {{23,2}}; // read database here if loading
        } else if (map.getLevel() == 2) {
            walls = level2Walls;
        }
        for(int[] coords: walls) {
            map.getCell(coords[0], coords[1]).setType(CellType.BREAKABLEWALL);
            map.setBreakableWall(coords[0], coords[1]);
        }
    }
}
