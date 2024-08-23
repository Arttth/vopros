package org.arta.vopros.db.impl;

import org.arta.vopros.db.Dao;
import org.arta.vopros.domain.Tag;
import org.arta.vopros.exception.DAOException;
import org.arta.vopros.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TagDao implements Dao<Tag, Long> {
    private static final TagDao INSTANCE = new TagDao();

    private static final String SAVE_SQL = """
        INSERT INTO tags (tag_name)
        VALUES (?);
        """;
    private static final String DELETE_SQL = """
        DELETE FROM tags
        WHERE tag_id = ?;
        """;
    private static final String UPDATE_SQL = """
        UPDATE tags
        SET tag_name = ?
        WHERE tag_id = ?;
        """;
    private static final String FIND_SQL = """
        SELECT tag_name
        FROM tags
        WHERE tag_id = ?
        """;
    private static final String FIND_ALL_SQL = """
        SELECT tag_name
        FROM tags
        """;
    @Override
    public Tag save(Tag tag) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, tag.getName());
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                tag.setId(keys.getLong("user_id"));
            }
            return tag;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL);
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean update(Tag tag) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);
            statement.setString(1, tag.getName());
            statement.setLong(2, tag.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Tag findById(Long id) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_SQL);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return buildTag(rs);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Tag> findAll() {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL);
            List<Tag> tags = new ArrayList<>();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                tags.add(buildTag(rs));
            }
            return tags;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private TagDao() {}

    public TagDao getInstance() {
        return INSTANCE;
    }

    private Tag buildTag(ResultSet rs) throws SQLException{
        return new Tag(
                rs.getLong("tag_id"),
                rs.getString("tag_name")
        );
    }
}
