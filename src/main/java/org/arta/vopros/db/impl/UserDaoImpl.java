package org.arta.vopros.db.impl;

import org.arta.vopros.db.UserDao;
import org.arta.vopros.domain.User;
import org.arta.vopros.exception.DAOException;
import org.arta.vopros.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class UserDaoImpl implements UserDao {

    @Override
    public void save(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public User getUser(Long id) {
        final String sql = "SELECT * FROM users WHERE user_id = ?;";
        Connection conn = ConnectionManager.open();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            String nickname = rs.getString("user_nickname");
            String user_name = rs.getString("user_name");
            String user_lastname = rs.getString("user_lastname");
            String user_education = rs.getString("user_education");
            String user_image = rs.getString("user_image");
            Integer user_reputation = rs.getInt("user_reputation");
            Date dateOfBirth = rs.getDate("user_dateOfBirth");
            User user = new User();
            return user;
        } catch (SQLException e) {
            throw new DAOException();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DAOException();
            }
        }
    }
}
