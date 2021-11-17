package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

public class ItemModel extends BaseModel{

    private final String name;
    private final int widthPos;
    private final int heightPos;

    public ItemModel (Item item) {
        name = item.getTileName();
        widthPos = item.getCell().getX();
        heightPos = item.getCell().getY();
    }

    public int getWidthPos() {
        return widthPos;
    }

    public int getHeightPos() {
        return heightPos;
    }

    public String getName() {
        return name;
    }
}
