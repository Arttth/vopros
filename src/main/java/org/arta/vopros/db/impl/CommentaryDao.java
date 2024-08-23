package org.arta.vopros.db.impl;

import org.arta.vopros.db.Dao;
import org.arta.vopros.domain.Commentary;
import org.arta.vopros.domain.Question;
import org.arta.vopros.domain.User;
import org.arta.vopros.exception.DAOException;
import org.arta.vopros.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentaryDao implements Dao<Commentary, Long> {
    private static final CommentaryDao INSTANCE = new CommentaryDao();
    private final static String SAVE_SQL = """
        INSERT INTO comments (comment_content, like_count, user_id, question_id)
        VALUES (?, ?, ?, ?);
        """;
    private final static String DELETE_SQL = """
        DELETE FROM comments
        WHERE comment_id = ?;
        """;
    private final static String UPDATE_SQL = """
        UPDATE comments
        SET comment_content = ?,
            like_count = ?,
            user_id = ?,
            question_id = ?
        WHERE comment_id = ?;
        """;
    private final static String FIND_SQL = """
        SELECT comment_content, like_count, user_id, question_id
        FROM comments
        WHERE comment_id = ?
        """;
    private final static String FIND_ALL_SQL = """
        SELECT comment_content, like_count, user_id, question_id
        FROM comments
        """;
    @Override
    public Commentary save(Commentary commentary) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, commentary.getContent());
            statement.setInt(2, commentary.getLikeCount());
            statement.setLong(3, commentary.getAuthor().getId());
            statement.setLong(4, commentary.getQuestion().getId());
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                commentary.setId(keys.getLong("comment_id"));
            }
            return commentary;
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
    public boolean update(Commentary commentary) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);
            statement.setString(1, commentary.getContent());
            statement.setInt(2, commentary.getLikeCount());
            statement.setLong(3, commentary.getAuthor().getId());
            statement.setLong(4, commentary.getQuestion().getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Commentary findById(Long id) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_SQL);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return buildCommentary(rs);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Commentary> findAll() {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL);
            List<Commentary> comments = new ArrayList<>();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                comments.add(buildCommentary(rs));
            }
            return comments;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private CommentaryDao(){}

    public static CommentaryDao getInstance() {
        return INSTANCE;
    }
    // TODO: full init user and question
    private Commentary buildCommentary(ResultSet rs) throws SQLException{
        User user = new User(rs.getLong("user_id"),
                null, null, null, null, null, null);
        Question question = new Question(rs.getLong("question_id"),
                null, null, null, null, null);
        return new Commentary(
                rs.getLong("comment_id"),
                rs.getString("comment_content"),
                rs.getInt("like_count"),
                user,
                question
        );
    }
}
