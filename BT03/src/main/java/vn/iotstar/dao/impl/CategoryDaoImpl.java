package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.configs.DBConnect;
import vn.iotstar.dao.CategoryDao;
import vn.iotstar.models.CategoryModel;

public class CategoryDaoImpl extends DBConnect implements CategoryDao {

	@Override
    public List<CategoryModel> findAllByUserId(String userUsername) {  // Sửa: String thay int
        List<CategoryModel> categories = new ArrayList<>();
        String sql = "SELECT * FROM category WHERE user_username = ? AND cate_name IS NOT NULL";  // Filter NULL
        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userUsername);  // Set string
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CategoryModel category = new CategoryModel();
                    category.setCateId(rs.getInt("cate_id"));
                    category.setCateName(rs.getString("cate_name"));
                    category.setIcons(rs.getString("icons"));
                    category.setUserUsername(rs.getString("user_username"));  // Set string
                    categories.add(category);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public CategoryModel findById(int cateId) {
        String sql = "SELECT * FROM category WHERE cate_id = ?";
        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cateId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getString("cate_name") != null) {  // Filter NULL
                    CategoryModel category = new CategoryModel();
                    category.setCateId(rs.getInt("cate_id"));
                    category.setCateName(rs.getString("cate_name"));
                    category.setIcons(rs.getString("icons"));
                    category.setUserUsername(rs.getString("user_username"));
                    return category;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(CategoryModel category) {
        String sql = "INSERT INTO category (cate_name, icons, user_username) VALUES (?, ?, ?)";  // Dùng user_username
        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, category.getCateName());
            ps.setString(2, category.getIcons());
            ps.setString(3, category.getUserUsername());  // Set string
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(CategoryModel category) {
        String sql = "UPDATE category SET cate_name = ?, icons = ? WHERE cate_id = ?";
        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, category.getCateName());
            ps.setString(2, category.getIcons());
            ps.setInt(3, category.getCateId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int cateId) {
        String sql = "DELETE FROM category WHERE cate_id = ?";
        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cateId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}