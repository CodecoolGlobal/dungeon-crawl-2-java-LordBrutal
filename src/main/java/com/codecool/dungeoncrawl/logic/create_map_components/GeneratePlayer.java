package com.codecool.dungeoncrawl.logic.create_map_components;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;


public class GeneratePlayer {

    public static void generatePlayer(GameMap gameMap) {
        int level = gameMap.getLevel();

        switch (level) {
            case 1:
                gameMap.setPlayer(new Player(gameMap.getCell(17, 11)));
                break;
            case 2:
                gameMap.setPlayer(new Player(gameMap.getCell(26, 27)));
                break;
        }
    }
}
