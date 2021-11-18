package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Sword;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class GameMapTest {
    GameMap gameMap = new GameMap(3,3,1,CellType.FLOOR);


    @Test
    void PlayerPickUpItem_IfPlayerAndItemCellTheSame_Success() {
        Player player = new Player(gameMap.getCell(1,1));
        gameMap.setPlayer(player);
        gameMap.getCell(1,1).setActor(player);
        ArrayList<Item> items = new ArrayList<>();
        Item item = new Sword(gameMap.getCell(1,1));
        items.add(item);
        gameMap.setItemsList(items);
        gameMap.getCell(1,1).setItem(item);

        gameMap.playerPickUpItem();

        int actualResult = gameMap.getItemsList().size();
        int expectedResult = 0;
        assertEquals(actualResult, expectedResult);
    }
}
