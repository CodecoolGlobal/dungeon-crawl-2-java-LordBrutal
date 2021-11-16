package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.WallModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class BreakableWallDaoJdbc implements BreakableWallDao {
    private DataSource dataSource;

    public BreakableWallDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(WallModel walls, int savedId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO remowable_walls (save_id, positions) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, savedId);
            statement.setString(2, walls.getPositions());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            walls.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(WallModel walls) {

    }

    @Override
    public WallModel get(int id) {
        return null;
    }

    @Override
    public List<WallModel> getAll() {
        return null;
    }
}
