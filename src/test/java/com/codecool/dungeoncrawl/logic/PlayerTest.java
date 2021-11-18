package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.PickAxe;
import com.codecool.dungeoncrawl.logic.items.Sword;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    Player player;
    GameMap gameMap;


    @BeforeEach
    public void createPlayerInstance() {
        gameMap = new GameMap(5, 5, 1, CellType.FLOOR);
        player = new Player(gameMap.getCell(2, 2));
    }

    @Test
    public void testInventoryAddingNull() {
        player.addToInventory();
        String expected = "[]";
        assertEquals(expected, player.getInventory().toString());
    }

    @Test
    public void testInventoryContainsPickedUpItem() {
        gameMap.getCell(2, 2).setItem(new Sword(gameMap.getCell(2,2)));
        player.addToInventory();
        List<String> expected = new ArrayList<>() {};

        ObservableList<String> actual = player.getInventory();
        expected.add("Sword");

        assertEquals(expected, actual);
    }

    @Test
    public void testGetItemsReturnsSimple() {
        gameMap.getCell(2, 2).setItem(new Sword(gameMap.getCell(2,2)));
        player.addToInventory();
        gameMap.getCell(2, 2).setItem(new PickAxe(gameMap.getCell(2,2)));
        player.addToInventory();

        String expected = "Sword PickAxe";
        String actual = player.getItems();

        assertEquals(expected, actual);
    }
}
