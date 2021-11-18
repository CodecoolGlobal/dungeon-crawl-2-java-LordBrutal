package com.codecool.dungeoncrawl.logic.create_map_components;

import com.codecool.dungeoncrawl.dao.EnemyTypes;
import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.enemys.Cyclops;
import com.codecool.dungeoncrawl.logic.actors.enemys.Enemy;
import com.codecool.dungeoncrawl.logic.actors.enemys.Skeleton;
import com.codecool.dungeoncrawl.logic.actors.enemys.Spider;
import com.codecool.dungeoncrawl.model.EnemyModel;

import java.util.ArrayList;
import java.util.List;

public class GenerateEnemys {

    public static void generateEnemy(GameDatabaseManager db,GameMap gameMap, int saveId){
        ArrayList<Enemy> enemysForMap = new ArrayList<>();
        List<EnemyModel> enemyModelList = db.loadEnemys(saveId);
        for (int i = 0; i < enemyModelList.size(); i++) {
            String enemyName = enemyModelList.get(i).getName();
            int x = enemyModelList.get(i).getX();
            int y = enemyModelList.get(i).getY();
            if (enemyName.equals(EnemyTypes.SPIDER.getEnemyName())){
                enemysForMap.add(new Spider(gameMap.getCell(x, y)));
            }else if(enemyName.equals(EnemyTypes.SKELETON.getEnemyName())){
                enemysForMap.add(new Skeleton(gameMap.getCell(x, y)));
            }else if(enemyName.equals(EnemyTypes.CYCLOPS.getEnemyName())){
                enemysForMap.add(new Cyclops(gameMap.getCell(x, y)));
            }
            enemysForMap.get(i).setHealth(enemyModelList.get(i).getHp());
        }
        gameMap.setEnemys2(enemysForMap);
    }

}
