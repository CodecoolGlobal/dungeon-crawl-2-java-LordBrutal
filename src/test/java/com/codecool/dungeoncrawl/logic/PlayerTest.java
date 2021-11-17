package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.enemys.Skeleton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    GameMap gameMap = new GameMap(3, 3, 1, CellType.FLOOR);

    @Test
    void moveUpdatesCells() {
        Player player = new Player(gameMap.getCell(1, 1));
        player.move(1, 0);

        assertEquals(2, player.getX());
        assertEquals(1, player.getY());
        assertNull(gameMap.getCell(1, 1).getActor());
        assertEquals(player, gameMap.getCell(2, 1).getActor());
    }

    @Test
    void cannotMoveIntoWall() {
        gameMap.getCell(2, 1).setType(CellType.WALL);
        Player player = new Player(gameMap.getCell(1, 1));
        player.move(1, 0);

        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
    }

    @Test
    void cannotMoveOutOfMap() {
        Player player = new Player(gameMap.getCell(2, 1));
        gameMap.setPlayer(player);

        player.move(1, 0);

        int expectedX = 3;
        int expectedY = 1;

        assertEquals(expectedX, player.getX());
        assertEquals(expectedY, player.getY());
    }

    @Test
    void cannotMoveIntoAnotherActor() {
        Player player = new Player(gameMap.getCell(1, 1));
        gameMap.setPlayer(player);
        Skeleton skeleton = new Skeleton(gameMap.getCell(2, 1));
        gameMap.setEnemys(skeleton);
        player.move(1, 0);
        int expectedPlayerX = 1;
        int expectedPlayerY = 1;

        int expectedEnemyX = 2;
        int expectedEnemyY = 1;
        assertTrue(player.getX() == expectedPlayerX && player.getY() == expectedPlayerY);
        assertTrue(skeleton.getX() == expectedEnemyX && skeleton.getY() == expectedEnemyY);
        assertEquals(skeleton, gameMap.getCell(2, 1).getActor());
    }
}
