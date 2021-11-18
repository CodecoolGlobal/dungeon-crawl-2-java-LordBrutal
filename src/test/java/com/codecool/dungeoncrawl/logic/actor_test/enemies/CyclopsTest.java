package com.codecool.dungeoncrawl.logic.actor_test.enemies;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.enemys.Cyclops;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CyclopsTest {
    Cyclops cyclops;
    GameMap gameMap;

    @BeforeEach
    public void instantiate() {
        gameMap = new GameMap(5,5,1, CellType.FLOOR);
        cyclops = new Cyclops(gameMap.getCell(3,1));
    }

    @Test
    void getPlayerPositionReturnsActualPosition() {
        Player player = new Player(gameMap.getCell(3, 3));
    }
}
