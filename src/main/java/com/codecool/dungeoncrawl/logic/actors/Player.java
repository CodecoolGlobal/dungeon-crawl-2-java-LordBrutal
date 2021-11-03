package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Potion;
import com.codecool.dungeoncrawl.logic.items.Shield;
import com.codecool.dungeoncrawl.logic.items.Sword;

import java.util.ArrayList;

public class Player extends Actor {
    private String name;

    private ArrayList<Item> inventory = new ArrayList<>();
    private int defense = 0;
    private boolean hasPickAxe = false;

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
                    setTileName("player with sword");
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
            }
            inventory.add(item);
            System.out.println(inventory);
            this.getCell().setItem(null);
        }
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public boolean getHasPickAxe() {
        return hasPickAxe;
    }

    public int getDefense() {
        return defense;
    }
}
