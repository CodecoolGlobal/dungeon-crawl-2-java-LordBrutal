package com.codecool.dungeoncrawl.logic.create_map_components;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;

import java.util.ArrayList;

public class BreakableWalls {
    public static void loadMapWalls(ArrayList<int[]> walls, GameMap map) {
        for(int[] coords: walls) {
            map.getCell(coords[0], coords[1]).setType(CellType.BREAKABLEWALL);
            map.setBreakableWall(coords[0], coords[1]);
        }
    }
}
