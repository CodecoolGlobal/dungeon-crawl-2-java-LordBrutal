package com.codecool.dungeoncrawl.logic.actor_test.enemies;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SkeletonTest {

    @Test
    public void getTileNameIsSkeleton() {
        GameMap gameMap = new GameMap(3, 3,1, CellType.FLOOR);
        Skeleton skeleton = new Skeleton(gameMap.getCell(1, 1));

        String expected = "skeleton";
        String actual = skeleton.getTileName();

        assertEquals(expected, actual);
    }
}
