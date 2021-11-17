package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.Player;

public class PlayerModel extends BaseModel {
    private String playerName;
    private int hp;
    private int def;
    private int x;
    private int y;
    private String items;

    public PlayerModel(String playerName, int x, int y) {
        this.playerName = playerName;
        this.x = x;
        this.y = y;
    }

    public PlayerModel(String playerName,int hp, int def,int x, int y) {
        this.playerName = playerName;
        this.hp = hp;
        this.x = x;
        this.y = y;
        this.def = def;
    }

    public PlayerModel(Player player) {
        this.playerName = player.getTileName();
        this.x = player.getX();
        this.y = player.getY();

        this.hp = player.getHealth();
        this.def = player.getDefense();
        this.items = player.getItems();

    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getHp() {
        return hp;
    }

    public int getDef() {
        return def;
    }

    public String getItems() {
        return items;
    }


    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
