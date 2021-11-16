package com.codecool.dungeoncrawl.logic.create_map_components;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.enemys.Cyclops;
import com.codecool.dungeoncrawl.logic.actors.enemys.Enemy;
import com.codecool.dungeoncrawl.logic.actors.enemys.Skeleton;
import com.codecool.dungeoncrawl.logic.actors.enemys.Spider;

import java.util.ArrayList;

public class GenerateEnemys {

    public static void generateEnemy(GameMap gameMap){
        int level = gameMap.getLevel();
        switch (level) {
            case 1:
                ArrayList<Enemy> enemys = new ArrayList<>();
                enemys.add(new Spider(gameMap.getCell(18, 2)));
                enemys.add(new Spider(gameMap.getCell(2, 8)));
                enemys.add(new Cyclops(gameMap.getCell(30, 11)));
                enemys.add(new Spider(gameMap.getCell(41, 11)));
                enemys.add(new Skeleton(gameMap.getCell(6, 17)));
                enemys.add(new Spider(gameMap.getCell(26, 20)));
                enemys.add(new Skeleton(gameMap.getCell(39, 20)));
                gameMap.setEnemys2(enemys);
                break;
            case 2:
                ArrayList<Enemy> map2Enemy = new ArrayList<>();
                map2Enemy.add(new Spider(gameMap.getCell(15, 1)));
                map2Enemy.add(new Spider(gameMap.getCell(30, 5)));
                map2Enemy.add(new Spider(gameMap.getCell(39, 9)));
                map2Enemy.add(new Spider(gameMap.getCell(16, 10)));
                map2Enemy.add(new Spider(gameMap.getCell(6, 11)));
                map2Enemy.add(new Spider(gameMap.getCell(28, 12)));
                map2Enemy.add(new Spider(gameMap.getCell(29, 12)));
                map2Enemy.add(new Skeleton(gameMap.getCell(39, 12)));
                map2Enemy.add(new Spider(gameMap.getCell(16, 15)));
                map2Enemy.add(new Cyclops(gameMap.getCell(24, 15)));
                map2Enemy.add(new Spider(gameMap.getCell(39, 15)));
                map2Enemy.add(new Spider(gameMap.getCell(8, 22)));
                map2Enemy.add(new Spider(gameMap.getCell(15, 22)));
                map2Enemy.add(new Spider(gameMap.getCell(36, 22)));
                map2Enemy.add(new Spider(gameMap.getCell(39, 22)));
                map2Enemy.add(new Spider(gameMap.getCell(9, 23)));
                map2Enemy.add(new Spider(gameMap.getCell(13, 23)));
                map2Enemy.add(new Spider(gameMap.getCell(12, 24)));
                map2Enemy.add(new Spider(gameMap.getCell(29, 24)));
                map2Enemy.add(new Skeleton(gameMap.getCell(37, 24)));
                map2Enemy.add(new Spider(gameMap.getCell(13, 26)));
                map2Enemy.add(new Skeleton(gameMap.getCell(5, 28)));
                map2Enemy.add(new Skeleton(gameMap.getCell(36, 29)));
                map2Enemy.add(new Spider(gameMap.getCell(38, 29)));
                gameMap.setEnemys2(map2Enemy);
                break;
        }
    }

}
