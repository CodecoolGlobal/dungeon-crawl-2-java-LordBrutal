package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.enemys.Cyclops;
import com.codecool.dungeoncrawl.logic.actors.enemys.Spider;
import com.codecool.dungeoncrawl.model.GameState;
import javafx.application.Application;
import javafx.geometry.Insets;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;

import java.util.List;
import java.util.Optional;


public class Main extends Application {
    GameDatabaseManager db = new GameDatabaseManager();
    GameMap map = MapLoader.loadMap(db, 1, 1);
    BorderPane borderPane;
    GridPane ui;
    Canvas canvas = new Canvas(
            Math.min(map.getWidth(), 30) * Tiles.TILE_WIDTH,
            Math.min(map.getHeight(), 22) * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Button pickUpButton = new Button("Pick up!");
    Button loadGameButton = new Button("Load Game");
    Label attackLabel = new Label();
    Label defenseLabel = new Label();
    ListView<String> list = new ListView<>();

    public Main() throws SQLException {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ui = addUi();

        borderPane = new BorderPane();

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
        map.playerPickUpItem();
        refresh();
    }

    private void loadGame() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Load Game");
        window.setMinWidth(400);

        Label label = new Label();
        label.setText("Chose Load");

        List<GameState> saves = db.loadGameStates();

        String [] arrayData = new String[saves.size()];
        for (int i = 0; i < saves.size(); i++) {
            arrayData[i] = saves.get(i).getName();
        }

        Dialog dialog = new ChoiceDialog(arrayData[0], arrayData);
        dialog.setTitle("Select load game");
        dialog.setHeaderText("Select your choice");

        Optional<String> result = dialog.showAndWait();
        String selected = "cancelled.";


        if (result.isPresent()) {

            selected = result.get();
            int level = 1;
            int saveId = 1;
            for(GameState save :saves) {
                if(save.getName().equals(selected)) {
                    level = save.getCurrentMap();
                    saveId = save.getId();
                }
            }
            startLevel(level, saveId);
        }



    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case S:
                if (keyEvent.isControlDown()) {
                    String savedName = getUserInputSaveName();
                    if (savedName.equals("")){
                        break;
                    }
                    db.save(map, savedName);
                }
                break;
            case UP:
                map.getPlayer().move(0, -1);
                makeEnemyMove();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                makeEnemyMove();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                makeEnemyMove();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                makeEnemyMove();
                break;
        }
        checkLevelWin();
        refresh();
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
        healthLabel.setText("" + map.getPlayer().getHealth());
        defenseLabel.setText("" + map.getPlayer().getDefense());
        if(map.getPlayer().getHealth() <= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Game Over");
            alert.setContentText("Restart?");
            alert.setHeaderText("You have died!");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                startLevel(1, 1);
            } else {
                System.exit(0);
            }
        }
    }

    private void makeEnemyMove(){
        for (int i = 0; i < map.getEnemys().size(); i++) {
            if (map.getEnemys().get(i) instanceof Spider){
                int[] nextStepSpider = ((Spider) map.getEnemys().get(i)).nextStep();
                map.getEnemys().get(i).move(nextStepSpider[0],nextStepSpider[1]);
            }else if(map.getEnemys().get(i) instanceof Cyclops){
                int[] calculateNextStep = ((Cyclops) map.getEnemys().get(i)).nextStepCyclops();
                map.getEnemys().get(i).move(calculateNextStep[0], calculateNextStep[1]);
            }
        }
    }

    private void checkLevelWin() {
        Player oldPlayer = map.getPlayer();
        int x = map.getPlayer().getX();
        int y = map.getPlayer().getY();
        if(map.getCell(x, y).getType().equals(CellType.DOOR)) {
            startLevel(2, 2);
            Cell playerCell = map.getPlayer().getCell();
            oldPlayer.setCell(playerCell);
            map.setPlayer(oldPlayer);
            map.getPlayer().removeKeyAndPickaxe();
            list.setItems(map.getPlayer().getInventory());

        }
    }

    private GridPane addUi() {
        list.setFocusTraversable(false);
        list.setItems(map.getPlayer().getInventory());
        pickUpButton.setFocusTraversable(false);
        pickUpButton.setOnMouseClicked(mouseEvent -> pickUpItem());
        loadGameButton.setFocusTraversable(false);
        loadGameButton.setOnMouseClicked(mouseEvent -> loadGame());
        GridPane ui = new GridPane();
        ui.setPrefWidth(400);
        ui.setPadding(new Insets(10));
        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label("Attack: "), 0, 1);
        ui.add(attackLabel, 1, 1);
        ui.add(new Label("Defense: "), 0, 2);
        ui.add(defenseLabel, 1,2);
        ui.add(pickUpButton, 0, 5);
        ui.add(loadGameButton, 0, 6);
        ui.add(new Label("Inventory"), 0, 3);
        ui.add(list, 0, 4);
        return ui;
    }

    private void startLevel(int level, int saveId) {

        map = MapLoader.loadMap(db ,level, saveId);
        canvas = new Canvas(
                Math.min(map.getWidth(), 35) * Tiles.TILE_WIDTH,
                Math.min(map.getHeight(), 22) * Tiles.TILE_WIDTH);
        healthLabel = new Label();
        pickUpButton = new Button("Pick up!");
        attackLabel = new Label();
        defenseLabel = new Label();
        list = new ListView<>();
        ui = addUi();
        borderPane.setRight(ui);
        refresh();
    }
    private String getUserInputSaveName(){
        Dialog dialog = new TextInputDialog("");
        dialog.setTitle("Save Game");
        dialog.setHeaderText("Please enter the save name");

        Optional<String> result = dialog.showAndWait();
        String entered = "none.";

        if (result.isPresent()) {
            entered = result.get();
            return entered;
        }
        return "";
    }
}
