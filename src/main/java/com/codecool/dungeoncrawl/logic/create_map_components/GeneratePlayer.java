package com.codecool.dungeoncrawl.logic.create_map_components;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.PlayerModel;


public class GeneratePlayer {

    public static void generatePlayer(GameDatabaseManager db,GameMap gameMap) {
        int level = gameMap.getLevel();
        PlayerModel player = db.loadPlayer(level);
        gameMap.setPlayer(new Player(gameMap.getCell(player.getX(), player.getY())));
        gameMap.getPlayer().setAttack(player.getAttack());
    }
}
