package com.codecool.dungeoncrawl.logic.actor_test.enemies;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.enemys.Cyclops;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {

    @Test
    public void testRemoveWall0() {
        GameMap gameMap = new GameMap(5,5,1, CellType.WALL);
        gameMap.getCell(3, 3).setType(CellType.FLOOR);
        Cyclops cyclops = new Cyclops(gameMap.getCell(3, 3));
        assertEquals(0, cyclops.removeWalls().size());

    }

    @Test
    public void testRemoveWallOneSize() {
        GameMap gameMap = new GameMap(5,5,1, CellType.WALL);
        gameMap.getCell(3, 3).setType(CellType.FLOOR);
        gameMap.getCell(3,2).setType(CellType.FLOOR);
        Cyclops cyclops = new Cyclops(gameMap.getCell(3, 3));

        assertEquals(1, cyclops.removeWalls().size());

    }
}
