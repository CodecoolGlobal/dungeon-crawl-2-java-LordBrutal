package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.enemys.Cyclops;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.enemys.Skeleton;
import com.codecool.dungeoncrawl.logic.create_map_components.BreakableWalls;
import com.codecool.dungeoncrawl.logic.items.*;
import com.codecool.dungeoncrawl.logic.actors.enemys.Spider;

import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(int level) {
        InputStream is = MapLoader.class.getResourceAsStream("/map" + level + ".txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            map.setEnemys(new Skeleton(cell));
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'c':
                            cell.setType(CellType.FLOOR);
                            map.setEnemys(new Cyclops(cell));
                            break;
                        case 'w':
                            cell.setType(CellType.FLOOR);
                            map.setEnemys(new Spider(cell));
                            break;
                        case '~':
                            cell.setType(CellType.WATER);
                            break;
                        case 'g':
                            cell.setType(CellType.GRASS);
                            break;
                        case 'T':
                            cell.setType(CellType.TREE);
                            break;
                        case 'h':
                            cell.setType(CellType.BUSH);
                            break;
                        case 'D':
                            cell.setType(CellType.DOOR);
                            break;
                        case 'B':
                            cell.setType(CellType.FLOOR);
                            new Key(cell, Color.BLUE.getColor());
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        generateItems(map);
        BreakableWalls.loadMapWalls(map,level);
        return map;
    }

    private static void generateItems(GameMap map) {
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
    }
}
