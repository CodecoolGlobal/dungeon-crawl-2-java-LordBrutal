package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class PlayerDaoJdbc implements PlayerDao {
    private DataSource dataSource;

    public PlayerDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(PlayerModel player, int savedId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO player (player_tile_name,save_id, saved_name, hp, def,  x, y, items) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, player.getPlayerName());
            statement.setInt(2, savedId);
            statement.setString(3, "tz");
            statement.setInt(4, player.getHp());
            statement.setInt(5, player.getDef());
            statement.setInt(6, player.getX());
            statement.setInt(7, player.getY());
            statement.setString(8, player.getItems());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            player.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(PlayerModel player) {

    }

    @Override
    public PlayerModel get(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM player WHERE save_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            PlayerModel playerModel = new PlayerModel(rs.getString(3),rs.getInt(5),rs.getInt(6), rs.getInt(7), rs.getInt(8));
            return playerModel;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading author with id: " + id, e);
        }
    }

    @Override
    public List<PlayerModel> getAll() {
        return null;
    }
}
