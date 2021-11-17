package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.EnemyModel;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class EnemyDaoJdbc implements EnemyDao{
    private DataSource dataSource;

    public EnemyDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public void add(EnemyModel enemy, int savedId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO enemy (name, save_id, hp, attack, x, y) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, enemy.getName());
            statement.setInt(2,savedId);
            statement.setInt(3, enemy.getHp());
            statement.setInt(4,enemy.getAttack());
            statement.setInt(5,enemy.getX());
            statement.setInt(6,enemy.getY());
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
    public List<EnemyModel> getAll() {
        return null;
    }

}
