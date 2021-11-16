package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.enemys.Cyclops;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.enemys.Enemy;

import java.util.ArrayList;

public class GameMap {

    private int level;
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;
    private ArrayList<Enemy> enemys = new ArrayList<>();

    public GameMap(int width, int height, int level, CellType defaultCellType) {
        this.level = level;
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLevel() {
        return level;
    }



    public Enemy getCyclops(){
        for (int i = 0; i < enemys.size(); i++) {
            if (enemys.get(i) instanceof Cyclops){
                return enemys.get(i);
            }
        }
        return null;
    }

    public ArrayList<Enemy> getEnemys(){
        return enemys;
    }

    public void setEnemys2(ArrayList<Enemy> enemys){
        this.enemys = enemys;
    }

    public void setEnemys(Enemy enemy){
        enemys.add(enemy);
    }
}
