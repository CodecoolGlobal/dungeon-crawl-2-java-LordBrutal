package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.enemys.Cyclops;
import com.codecool.dungeoncrawl.logic.actors.enemys.Spider;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            Math.min(map.getWidth(), 35) * Tiles.TILE_WIDTH,
            Math.min(map.getHeight(), 22) * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Button pickUpButton = new Button("Pick up!");
    Label attackLabel = new Label();
    Label defenseLabel = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label("Attack: "), 0, 1);
        ui.add(attackLabel, 1, 1);
        ui.add(new Label("Defense: "), 0, 2);
        ui.add(defenseLabel, 1,2);
        pickUpButton.setFocusTraversable(false);
        pickUpButton.setOnMouseClicked(mouseEvent -> pickUpItem());
        ui.add(pickUpButton, 0, 3);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void pickUpItem() {
        map.getPlayer().addToInventory();
        refresh();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                makeEnemyMove();
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                makeEnemyMove();
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                makeEnemyMove();
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                makeEnemyMove();
                refresh();
                break;
        }
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        int centerX = (int)(canvas.getWidth()/(Tiles.TILE_WIDTH*2));
        int centerY = (int)(canvas.getHeight()/(Tiles.TILE_WIDTH*2)) - 1;
        int[] shift = new int[2];
        if(map.getPlayer().getX() > centerX){
            shift[0] = map.getPlayer().getX() - centerX;
        }
        if(map.getPlayer().getY() > centerY) {
            shift[1] = map.getPlayer().getY() - centerY;
        }
        for (int x = 0; x+shift[0] < map.getWidth(); x++) {
            for (int y = 0; y+shift[1] < map.getHeight(); y++) {
                Cell cell = map.getCell(x+shift[0], y+shift[1]);
                if (cell.getActor() != null) {

                    if(cell.getActor().getHealth() <= 0) {
                        cell.setActor(null);
                        Tiles.drawTile(context, cell, x, y);
                    } else {
                        Tiles.drawTile(context, cell.getActor(), x, y);
                    }
                }
                else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                }
                else {

                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        attackLabel.setText("" + map.getPlayer().getAttack());
        healthLabel.setText("❤".repeat(map.getPlayer().getHealth()));
        defenseLabel.setText("" + map.getPlayer().getDefense());
    }

    private void makeEnemyMove(){
        for (int i = 0; i < map.getEnemys().size(); i++) {
            if (map.getEnemys().get(i) instanceof Spider){
                int[] nextStepSpider = ((Spider) map.getEnemys().get(i)).nextStep();
                map.getEnemys().get(i).move(nextStepSpider[0],nextStepSpider[1]);
            }else if(map.getEnemys().get(i) instanceof Cyclops){
                int[] calculateNextStep = ((Cyclops) map.getEnemys().get(i)).nextStepCyclop();
                map.getEnemys().get(i).move(calculateNextStep[0], calculateNextStep[1]);
            }
        }
    }
}
