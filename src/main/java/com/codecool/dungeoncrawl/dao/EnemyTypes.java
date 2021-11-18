package com.codecool.dungeoncrawl.dao;

public enum EnemyTypes {
    SPIDER("spider"),
    SKELETON("skeleton"),
    CYCLOPS("cyclops");

    private final String enemyName;

    EnemyTypes(String enemyName) {
        this.enemyName = enemyName;
    }

    public String getEnemyName() {
        return enemyName;
    }
}
