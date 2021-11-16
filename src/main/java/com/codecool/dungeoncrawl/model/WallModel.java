package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.GameMap;

public class WallModel extends BaseModel {
    private String positions;

    public WallModel(GameMap map) {
        this.positions = map.getBreakableWalls();
    }

    public String getPositions() {
        return positions;
    }
}
