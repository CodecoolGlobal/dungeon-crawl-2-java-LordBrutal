package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.codecool.dungeoncrawl.model.WallModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;

public class GameDatabaseManager {
    private GameStateDao gameState;
    private PlayerDao playerDao;
    private BreakableWallDao wallDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameState = new GameStateDaoJdbc(dataSource);
        wallDao = new BreakableWallDaoJdbc(dataSource);
    }

    public void save(GameMap map) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        GameState GameStateModel = new GameState("map1.txt", timestamp);
        gameState.add(GameStateModel);
        int saveId = GameStateModel.getId();
        PlayerModel model = new PlayerModel(map.getPlayer());
        playerDao.add(model, saveId);
        WallModel walls = new WallModel(map);
        wallDao.add(walls, saveId);
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = System.getenv("PSQL_DB_NAME");
        String user = System.getenv("PSQL_USER_NAME");
        String password = System.getenv("PSQL_PASSWORD");
        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
