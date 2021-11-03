package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;

public class Player extends Actor {

    private ArrayList<Item> inventory = new ArrayList<>();

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
            inventory.add(item);
            System.out.println(inventory);
            this.getCell().setItem(null);
        }
    }

}
