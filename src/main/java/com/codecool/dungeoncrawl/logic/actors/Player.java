package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Potion;
import com.codecool.dungeoncrawl.logic.items.Shield;
import com.codecool.dungeoncrawl.logic.items.Sword;

import java.util.ArrayList;

public class Player extends Actor {

    private ArrayList<Item> inventory = new ArrayList<>();
    private int defense = 0;

    public Player(Cell cell) {
        super(cell);
        this.attack = 5;
    }


    public int getAttack() {
        return attack;
    }

    public String getTileName() {
        return "player";
    }

    public void addToInventory() {
        Item item = this.getCell().getItem();
        if (item != null) {
            String itemClass = item.getClass().getSimpleName();
            switch (itemClass) {
                case "Sword":
                    this.attack += ((Sword)item).getAttackModifier();
                    break;
                case "Shield":
                    defense += ((Shield) item).getDefenseModifier();
                    break;
                case "Potion":
                    setHealth(this.getHealth()+((Potion) item).getHealthIncrease());
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
}
