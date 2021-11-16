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
    private ArrayList<int[]> breakableWalls = new ArrayList<>();

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
        for (Enemy enemy : enemys) {
            if (enemy instanceof Cyclops) {
                return enemy;
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

    public void setBreakableWall(int x, int y) {
        breakableWalls.add(new int[] {x, y});
    }

    public String getBreakableWalls() {
        StringBuilder str = new StringBuilder();
        for(int[] coords: breakableWalls) {
            str.append(coords[0]).append(',').append(coords[1]).append(" ");
        }
        str.deleteCharAt(-1);
        return String.valueOf(str);
    }

    public void removeBreakableWall(Cell wallCell) {
        int x = wallCell.getX();
        int y = wallCell.getY();
        breakableWalls.remove(new int[]{x, y});
    }
}
