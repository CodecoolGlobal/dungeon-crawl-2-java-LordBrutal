package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.EnemyModel;
import com.codecool.dungeoncrawl.model.ItemModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoJdbc implements ItemDao{

    private DataSource dataSource;

    public ItemDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ItemModel item, int savedId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO item (item_tile_name, width_position, height_position, save_id) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getName());
            statement.setInt(2, item.getWidthPos());
            statement.setInt(3, item.getHeightPos());
            statement.setInt(4, savedId);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            item.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(ItemModel item) {

    }

    @Override
    public ItemModel get(int id) {
        return null;
    }

    @Override
    public List<ItemModel> getAll(int saveId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, item_tile_name, width_position, height_position FROM item WHERE save_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, saveId);
            ResultSet rs = st.executeQuery();
            List<ItemModel> result = new ArrayList<>();
            while (rs.next()) { // while result set pointer is positioned before or on last row read authors
                ItemModel itemModel = new ItemModel(rs.getString(2),rs.getInt(3), rs.getInt(4));
                itemModel.setId(rs.getInt(1));
                result.add(itemModel);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all authors", e);
        }
    }
}
