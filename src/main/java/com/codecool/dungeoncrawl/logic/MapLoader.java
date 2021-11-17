package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.create_map_components.BreakableWalls;
import com.codecool.dungeoncrawl.logic.create_map_components.GenerateEnemys;
import com.codecool.dungeoncrawl.logic.create_map_components.GenerateItems;
import com.codecool.dungeoncrawl.logic.create_map_components.GeneratePlayer;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(GameDatabaseManager db, int level, boolean isLoading) {

        InputStream is = MapLoader.class.getResourceAsStream("/map" + level + ".txt");
        assert is != null;
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, level, CellType.EMPTY);
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
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
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
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        int saveId = 1;
        if(isLoading) {
//            BreakableWalls.loadMapWalls(db, map, saveId);
//            GenerateEnemys.generateEnemy(db, map, saveId);
//            GenerateItems.generateItems(db, map, saveId); //load items
//            GeneratePlayer.generatePlayer(db, map, saveId);
        } else {
            BreakableWalls.loadMapWalls(db, map, level);
//            GenerateEnemys.generateEnemy(db, map, level);
//            GenerateItems.generateItems(map);
            GeneratePlayer.generatePlayer(db, map, level);
        }

        return map;
    }
}
