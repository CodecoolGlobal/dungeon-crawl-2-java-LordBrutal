package com.codecool.dungeoncrawl.logic.create_map_components;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.PlayerModel;


public class GeneratePlayer {

    public static void generatePlayer(PlayerModel player, GameMap gameMap) {
            gameMap.setPlayer(new Player(gameMap.getCell(player.getX(), player.getY())));
            gameMap.getPlayer().setAttack(player.getAttack());

    }
}
