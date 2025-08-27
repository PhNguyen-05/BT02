package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vn.iotstar.configs.DBConnect;
import vn.iotstar.dao.UserDao;
import vn.iotstar.models.UserModel;

public class UserDaoImpl implements UserDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public UserModel findByUsername(String username) {
        String sql = "SELECT * FROM [users] WHERE username = ?";
        try (Connection conn = new DBConnect().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    UserModel users = new UserModel();
                    users.setId(rs.getInt("id"));
                    users.setEmail(rs.getString("email"));
                    users.setUserName(rs.getString("username"));
                    users.setFullName(rs.getString("fullname"));
                    users.setPassWord(rs.getString("password"));
                    users.setAvatar(rs.getString("avatar"));
                    return users;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
