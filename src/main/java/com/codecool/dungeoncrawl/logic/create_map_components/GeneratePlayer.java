package com.codecool.dungeoncrawl.logic.create_map_components;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.PlayerModel;


public class GeneratePlayer {

    public static void generatePlayer(PlayerModel playerModel, GameMap gameMap) {
        Player player = new Player(gameMap.getCell(playerModel.getX(), playerModel.getY()));
        gameMap.setPlayer(player);
        player.setAttack(playerModel.getAttack());
        player.setHealth(playerModel.getHp());
        player.setDefense(playerModel.getDef());
        String items = playerModel.getItems();
        player.setTileName(playerModel.getPlayerName());
        if(!items.equals("")) {
            for(String item: items.split(",")) {
                switch (item) {
                    case "Sword":
                        player.setHasSword(true);
                        break;
                    case "PickAxe":
                        player.setHasPickAxe(true);
                        break;
                    case "Key":
                        player.setHasKey(true);
                        break;
                }
            }
            player.setInventory(items.split(","));
        }
    }
}
