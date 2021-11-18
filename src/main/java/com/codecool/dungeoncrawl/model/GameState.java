package com.codecool.dungeoncrawl.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class GameState extends BaseModel {
    private Timestamp savedAt;
    private int currentMap;
    private List<String> discoveredMaps = new ArrayList<>();
    private String name;

    public GameState(int currentMap, Timestamp savedAt, String name) {
        this.currentMap = currentMap;
        this.savedAt = savedAt;
        this.name = name;
    }

    public Timestamp getSavedAt() {
        return savedAt;
    }

    public String getName() {
        return name;
    }

    public void setSavedAt(Timestamp savedAt) {
        this.savedAt = savedAt;
    }

    public int getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(int currentMap) {
        this.currentMap = currentMap;
    }

    public List<String> getDiscoveredMaps() {
        return discoveredMaps;
    }

    public void addDiscoveredMap(String map) {
        this.discoveredMaps.add(map);
    }
}
