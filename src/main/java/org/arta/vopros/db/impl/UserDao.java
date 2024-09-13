package org.arta.vopros.db.impl;

import org.arta.vopros.db.Dao;
import org.arta.vopros.domain.Role;
import org.arta.vopros.domain.User;
import org.arta.vopros.exception.DAOException;
import org.arta.vopros.dto.UserFilter;
import org.arta.vopros.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserDao implements Dao<User, Long> {
    private final static UserDao INSTANCE = new UserDao();
    private static final String SAVE_SQL = """
            INSERT INTO users
            (user_nickname, user_name, user_lastname, date_of_birth, profile_photo, reputation, email, password, role)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);
            """;
    private static final String DELETE_SQL = """
            DELETE FROM users
            WHERE user_id = ?;
            """;
    private static final String UPDATE_SQL = """
            UPDATE users
            SET user_nickname = ?,
                user_name = ?,
                user_lastname = ?,
                date_of_birth = ?,
                profile_photo = ?,
                reputation = ?,
                email = ?,
                password = ?,
                role = ?
            WHERE user_id = ?;
            """;
    private static final String FIND_ALL_SQL = """
            SELECT user_nickname, user_name, user_lastname, date_of_birth, profile_photo, reputation, email, password, role
              FROM users
            """;
    private static final String FIND_SQL = FIND_ALL_SQL + """
              WHERE user_id = ?;
            """;
    private static final String FIND_USER_BY_PASSWORD_AND_EMAIL = """
            SELECT * FROM users WHERE email = ? AND password = ?;
            """;

    private static final String FIND_USER_BY_NICKNAME = """
                SELECT * FROM users WHERE user_nickname = ?;
            """;

    public Optional<User> findUserByNickname(String nickname) {
        try (Connection connection = ConnectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_NICKNAME);
            preparedStatement.setString(1, nickname);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return Optional.ofNullable(buildUser(rs));
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public Optional<User> getUserByEmailAndPassword(String email, String password) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_PASSWORD_AND_EMAIL);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            User user = null;
            if (rs.next()) {
                user = buildUser(rs);
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
    public List<User> findAll(UserFilter userFilter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();
        if (userFilter.user_nickname() != null) {
            parameters.add("%" + userFilter.user_nickname() + "%");
            whereSql.add("user_nickname like ?");
        }
        if (userFilter.user_name() != null) {
            parameters.add("%" + userFilter.user_name() + "%");
            whereSql.add("user_name like ?");
        }
        if (userFilter.user_lastname() != null) {
            parameters.add("%" + userFilter.user_lastname() + "%");
            whereSql.add("user_lastname like ?");
        }
        parameters.add(userFilter.limit());
        parameters.add(userFilter.offset());
        String where = whereSql.stream().collect(Collectors.joining(
                " AND ",
                parameters.size() > 2 ? " WHERE " : " ",
                " limit ? offset ?;"
        ));
        String sql = FIND_ALL_SQL + where;
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < parameters.size(); ++i) {
                statement.setObject(i + 1, parameters.get(i));
            }
            List<User> users = new ArrayList<>();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                users.add(buildUser(rs));
            }
            return users;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<User> findAll() {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL);
            List<User> users = new ArrayList<>();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                users.add(buildUser(rs));
            }
            return users;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean update(User user) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);
            statement.setString(1, user.getNickname());
            statement.setString(2, user.getName());
            statement.setString(3, user.getLastname());
            statement.setDate(4, Date.valueOf(user.getDateOfBirth()));
            statement.setString(5, user.getProfilePhoto());
            statement.setInt(6, user.getReputation());
            statement.setString(7, user.getEmail());
            statement.setString(8, user.getPassword());
            statement.setLong(9, user.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(Long user_id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL);
            statement.setLong(1, user_id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public User save(User user) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getNickname());
            statement.setString(2, user.getName());
            statement.setString(3, user.getLastname());
            statement.setDate(4, Date.valueOf(user.getDateOfBirth()));
            statement.setString(5, user.getProfilePhoto());
            statement.setInt(6, user.getReputation());
            statement.setString(7, user.getEmail());
            statement.setString(8, user.getPassword());
            statement.setString(9, user.getRole().name());
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                user.setId(keys.getLong("user_id"));
            }
            return user;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public User findById(Long id) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_SQL);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            User user = buildUser(rs);
            return user;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private UserDao() {}

    public static UserDao getInstance() {
        return INSTANCE;
    }
    private static User buildUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getLong("user_id"),
                rs.getString("user_nickname"),
                rs.getString("user_name"),
                rs.getString("user_lastname"),
                rs.getDate("date_of_birth").toLocalDate(),
                rs.getString("profile_photo"),
                rs.getInt("reputation"),
                rs.getString("email"),
                rs.getString("password"),
                Role.find(rs.getString("role")).get()
        );
    }
}
