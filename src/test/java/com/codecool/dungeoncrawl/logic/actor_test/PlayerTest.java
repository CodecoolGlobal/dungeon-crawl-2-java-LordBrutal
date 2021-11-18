package com.codecool.dungeoncrawl.logic.actor_test;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
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
    void testPlayerHealthAtCreation() {
        int expected = 10;
        int actual = player.getHealth();
        assertEquals(expected, actual);
    }

    @Test
    public void testInventoryAddingNull() {
        player.addToInventory(null);
        String expected = "[]";
        assertEquals(expected, player.getInventory().toString());
    }

    @Test
    public void testInventoryContainsPickedUpItem() {
        player.addToInventory(new Sword(gameMap.getCell(2,2)));
        List<String> expected = new ArrayList<>() {};

        ObservableList<String> actual = player.getInventory();
        expected.add("Sword");

        assertEquals(expected, actual);
    }

    @Test
    public void testGetItemsReturnsSimple() {
        player.addToInventory(new Sword(gameMap.getCell(2,2)));
        player.addToInventory(new PickAxe(gameMap.getCell(2,2)));

        String expected = "Sword PickAxe";
        String actual = player.getItems();

        assertEquals(expected, actual);
    }
}
