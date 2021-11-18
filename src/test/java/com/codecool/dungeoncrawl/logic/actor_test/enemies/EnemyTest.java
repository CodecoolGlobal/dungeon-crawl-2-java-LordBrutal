package com.codecool.dungeoncrawl.logic.actor_test.enemies;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.enemys.Enemy;
import com.codecool.dungeoncrawl.logic.actors.enemys.Skeleton;
import com.codecool.dungeoncrawl.logic.create_map_components.GenerateItems;
import com.codecool.dungeoncrawl.logic.items.Item;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {

    @Test
    public void testRemoveWalls() {
        GameMap gameMap = new GameMap(5,5,1, CellType.FLOOR);
        Skeleton skeleton = new Skeleton(gameMap.getCell(3, 3));
        // I want to know if the remove walls method includes a wall.
        //what does this method actually do?
    }
}
