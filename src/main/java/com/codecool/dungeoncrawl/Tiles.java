package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("bush", new Tile(0,2));
        tileMap.put("tree", new Tile(3,2));
        tileMap.put("spider", new Tile(30,5));
        tileMap.put("cyclops", new Tile(30,6));
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(25, 0));
        tileMap.put("player with sword", new Tile(27, 0));
        tileMap.put("player with two swords", new Tile(30, 2));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("sword", new Tile(0, 29));
        tileMap.put("shield", new Tile(7, 26));
        tileMap.put("blue key", new Tile(17, 23));
        tileMap.put("breakable wall", new Tile(0, 13));
        tileMap.put("water", new Tile(8, 5));
        tileMap.put("grass", new Tile(6, 0));
        tileMap.put("potion", new Tile(17, 25));
        tileMap.put("door", new Tile(5, 10));
        tileMap.put("pickaxe", new Tile(11, 27));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
