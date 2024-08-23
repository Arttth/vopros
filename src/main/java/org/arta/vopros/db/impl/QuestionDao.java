package org.arta.vopros.db.impl;

import org.arta.vopros.db.Dao;
import org.arta.vopros.domain.Discipline;
import org.arta.vopros.domain.Question;
import org.arta.vopros.domain.Solution;
import org.arta.vopros.domain.User;
import org.arta.vopros.exception.DAOException;
import org.arta.vopros.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao implements Dao<Question, Long> {
    private final static QuestionDao INSTANCE = new QuestionDao();
    private final static String SAVE_SQL = """
        INSERT INTO questions (question_name, quesion_main_part, like_count, user_id, discipline_id)
        VALUES (?, ?, ?, ?, ?);
        """;
    private final static String DELETE_SQL = """
        DELETE FROM questions
        WHERE question_id = ?;
        """;
    private final static String UPDATE_SQL = """
        UPDATE questions
        SET question_name = ?,
            quesion_main_part = ?,
            like_count = ?,
            user_id = ?,
            discipline_id = ?
        WHERE question_id = ?;
        """;
    private final static String FIND_SQL = """
        SELECT question_name, quesion_main_part, like_count, user_id, discipline_id
        FROM questions
        WHERE question_id = ?
        """;
    private final static String FIND_ALL_SQL = """
        SELECT question_name, quesion_main_part, like_count, user_id, discipline_id
        FROM questions
        """;

    @Override
    public Question save(Question question) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, question.getName());
            statement.setString(2, question.getQuestionMainPart());
            statement.setInt(3, question.getLikeCount());
            statement.setLong(4, question.getAuthor().getId());
            statement.setLong(5, question.getDiscipline().getId());
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                question.setId(keys.getLong("question_id"));
            }
            return question;
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
    public boolean update(Question question) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);
            statement.setString(1, question.getName());
            statement.setString(2, question.getQuestionMainPart());
            statement.setInt(3, question.getLikeCount());
            statement.setLong(4, question.getAuthor().getId());
            statement.setLong(5, question.getDiscipline().getId());
            statement.setLong(6, question.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Question findById(Long id) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_SQL);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return buildQuestion(rs);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Question> findAll() {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL);
            List<Question> questions = new ArrayList<>();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                questions.add(buildQuestion(rs));
            }
            return questions;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private QuestionDao() {}

    public static QuestionDao getInstance() {
        return INSTANCE;
    }
    private Question buildQuestion(ResultSet rs) throws SQLException{
        User user = new User(rs.getLong("user_id"),
                null, null, null, null, null, null);
        Discipline discipline = new Discipline(rs.getLong("discipline_id"),
                null, null);
        return new Question(
                rs.getLong("question_id"),
                rs.getString("question_name"),
                rs.getString("question_main_part"),
                rs.getInt("like_count"),
                user,
                discipline
        );
    }
}
