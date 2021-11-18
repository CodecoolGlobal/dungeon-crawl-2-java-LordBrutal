package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.EnemyModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnemyDaoJdbc implements EnemyDao{
    private DataSource dataSource;

    public EnemyDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public void add(EnemyModel enemy, int savedId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO enemy (name, save_id, hp, x, y) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, enemy.getName());
            statement.setInt(2,savedId);
            statement.setInt(3, enemy.getHp());
            statement.setInt(4,enemy.getX());
            statement.setInt(5,enemy.getY());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            enemy.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(EnemyModel enemy) {

    }

    @Override
    public EnemyModel get(int id) {
        return null;
    }

    @Override
    public List<EnemyModel> getAll(int saveId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM enemy WHERE save_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, saveId);
            ResultSet rs = st.executeQuery();
            List<EnemyModel> result = new ArrayList<>();
            while (rs.next()) { // while result set pointer is positioned before or on last row read authors
                EnemyModel enemyModel = new EnemyModel(rs.getString(2),rs.getInt(3), rs.getInt(5), rs.getInt(6));
                enemyModel.setId(rs.getInt(1));
                result.add(enemyModel);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all authors", e);
        }
    }

}
