package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.WallModel;

import java.util.List;

public interface BreakableWallDao {
    void add(WallModel walls, int savedId);
    void update(WallModel walls);
    WallModel get(int id);
    List<WallModel> getAll();
}
