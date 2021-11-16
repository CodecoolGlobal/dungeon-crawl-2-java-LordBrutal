package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Player extends Actor {
    private String name;

    private ObservableList<String> inventory = FXCollections.observableArrayList();
    private int defense = 0;
    private boolean hasPickAxe = false;
    private boolean hasKey = false;
    private boolean hasSword = false;

    public Player(Cell cell) {
        super(cell);
        this.attack = 5;
        this.name = "player";
    }


    public int getAttack() {
        return attack;
    }

    public String getTileName() {
        return this.name;
    }

    public void setTileName(String newName) {
        this.name = newName;
    }

    public void addToInventory() {
        Item item = this.getCell().getItem();
        if (item != null) {
            String itemClass = item.getClass().getSimpleName();
            switch (itemClass) {
                case "Sword":
                    this.attack += ((Sword)item).getAttackModifier();
                    if(hasSword) {
                        setTileName("player with two swords");
                    } else {
                        setTileName("player with sword");
                        hasSword = true;
                    }
                    break;
                case "Shield":
                    defense += ((Shield) item).getDefenseModifier();
                    break;
                case "Potion":
                    setHealth(this.getHealth()+((Potion) item).getHealthIncrease());
                    break;
                case "PickAxe":
                    hasPickAxe = true;
                    break;
                case "Key":
                    hasKey = true;
                    break;
            }
            if (item instanceof Key) {
                inventory.add(String.format("%s%s key", ((Key) item).getColor().substring(0, 1).toUpperCase(), ((Key) item).getColor().substring(1)));
            }
            else inventory.add(item.getClass().getSimpleName());
            this.getCell().setItem(null);
        }
    }

    public ObservableList<String> getInventory() {
        return inventory;
    }

    public boolean getHasPickAxe() {
        return hasPickAxe;
    }

    public boolean getHasKey() {
        return hasKey;
    }

    public int getDefense() {
        return defense;
    }
}
