package com.codecool.dungeoncrawl.logic.create_map_components;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Color;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.items.*;

import java.util.Random;

public class GenerateItems {

    public static void generateItems(GameMap map) {

        int keyWidthPosition;
        int keyHeightPosition;
        Cell keyCell = null;
        int level = map.getLevel();
        String[] items = { "Potion", "PickAxe", "Shield", "Sword"};
        for(String item: items) {
            while (true) {
                Random random1 = new Random();
                int h = random1.nextInt(map.getHeight());
                Random random2 = new Random();
                int w = random2.nextInt(map.getWidth());
                Cell cell = map.getCell(w, h);
                if (cell.getType().equals(CellType.FLOOR) && cell.getActor() == null && cell.getItem() == null) {
                    switch (item) {
                        case "Potion":
                            new Potion(cell);
                            break;
                        case "PickAxe":
                            new PickAxe(cell);
                            break;
                        case "Shield":
                            new Shield(cell);
                            break;
                        case "Sword":
                            new Sword(cell);
                            break;
                    }
                    break;
                }
            }
        }
        switch (level) {
            case 1:
                keyWidthPosition = 4;
                keyHeightPosition = 6;
                keyCell = map.getCell(keyWidthPosition, keyHeightPosition);
                break;
            case 2:
                keyWidthPosition = 37;
                keyHeightPosition = 25;
                keyCell = map.getCell(keyWidthPosition, keyHeightPosition);
                break;
        }
        new Key(keyCell, Color.BLUE.getColor());
    }
}
