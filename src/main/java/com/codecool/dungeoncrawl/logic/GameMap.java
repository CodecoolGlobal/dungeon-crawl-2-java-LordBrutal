package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.enemys.Cyclops;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.enemys.Enemy;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GameMap {

    private int level;
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;
    private ArrayList<Enemy> enemys = new ArrayList<>();
    private ArrayList<int[]> breakableWalls = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

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

    public void playerPickUpItem() {
        Item grabbedItem = null;
        for (Item item : items) {
            if (player.getCell().equals(item.getCell())) {
                player.addToInventory(item);
                grabbedItem = item;
            }
        }
        if (grabbedItem!=null) {
            items.remove(grabbedItem);
            grabbedItem.getCell().setItem(null);
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

    public ArrayList<Item> getItemsList() {
        return items;
    }

    public void setItemsList(ArrayList<Item> items) {
        this.items = items;
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
        StringBuilder sb = new StringBuilder();
        String prefix = "";
        for (int[] coords: breakableWalls) {
            sb.append(prefix);
            prefix = " ";
            sb.append(coords[0]).append(",").append(coords[1]);
        }
        return String.valueOf(sb);
    }

    public void removeBreakableWall(Cell wallCell) {
        int[] filtered = {wallCell.getX(), wallCell.getY()};
        breakableWalls = (ArrayList<int[]>) breakableWalls.stream()
                .filter(w -> !Arrays.equals(Arrays.stream(w).toArray(), filtered))
                .collect(Collectors.toList());
    }
}
