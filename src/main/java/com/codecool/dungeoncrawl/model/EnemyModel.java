package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.enemys.Enemy;

public class EnemyModel extends BaseModel{
    private String name;
    private int hp;
    private int x;
    private int y;

    public EnemyModel(Enemy enemy){
        this.name = enemy.getTileName();
        this.hp = enemy.getHealth();
        this.x = enemy.getX();
        this.y = enemy.getY();
    }

    public EnemyModel(String name, int hp, int x, int y){
        this.name = name;
        this.hp = hp;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
