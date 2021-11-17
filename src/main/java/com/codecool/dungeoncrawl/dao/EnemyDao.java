package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.EnemyModel;

import java.util.List;


public interface EnemyDao {
    void add(EnemyModel enemy, int savedId);
    void update(EnemyModel enemy);
    EnemyModel get(int id);
    List<EnemyModel> getAll();
}
