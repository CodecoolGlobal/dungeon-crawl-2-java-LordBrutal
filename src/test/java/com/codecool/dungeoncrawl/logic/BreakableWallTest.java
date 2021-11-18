package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.create_map_components.BreakableWalls;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BreakableWallTest {
    GameMap map;

    @BeforeEach
    public void init() {
        map = new GameMap(3, 3, 1, CellType.FLOOR);
        ArrayList<int[]> coordinate = new ArrayList<>();
        coordinate.add(new int[] {0, 0});
        BreakableWalls.loadMapWalls(coordinate, map);
    }

    @Test
    void loadMapBreakableWalls_OneBlock() {
        assertEquals(CellType.BREAKABLEWALL, map.getCell(0, 0).getType());
    }

    @Test
    void removeBreakableWall_removeOne() {
        assertEquals("0,0", map.getBreakableWalls());
        Cell breakableWall = map.getCell(0, 0);
        map.removeBreakableWall(breakableWall);
        assertEquals(0, map.getBreakableWalls().length());
    }
}