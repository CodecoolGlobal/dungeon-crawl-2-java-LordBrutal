package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.WallModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
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
    public ArrayList<int[]> get(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT positions FROM remowable_walls WHERE save_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            String walls = rs.getString(1);
            return getPositions(walls);
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading author with id: " + id, e);
        }
    }

    @Override
    public List<WallModel> getAll() {
        return null;
    }

    private ArrayList<int[]> getPositions(String positions) {
        String[] coordinates = positions.split(" ");
        ArrayList<int[]> result = new ArrayList<>();
        for (String coordinate : coordinates) {
            int x = Integer.parseInt(coordinate.split(",")[0]);
            int y = Integer.parseInt(coordinate.split(",")[1]);
            result.add(new int[]{x, y});
        }
        return result;
    }
}
