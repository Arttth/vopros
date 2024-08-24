package org.arta.vopros.db.impl;

import org.arta.vopros.db.Dao;
import org.arta.vopros.domain.Question;
import org.arta.vopros.domain.Solution;
import org.arta.vopros.exception.DAOException;
import org.arta.vopros.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolutionDao implements Dao<Solution, Long> {
    private static final SolutionDao INSTANCE = new SolutionDao();
    private final static String SAVE_SQL = """
        INSERT INTO solutions (solution_text, solution_file, question_id)
        VALUES (?, ?, ?);
        """;
    private final static String DELETE_SQL = """
        DELETE FROM solutions
        WHERE solutions.solution_id = ?;
        """;
    private final static String UPDATE_SQL = """
        UPDATE solutions
        SET solution_text = ?,
            solution_file = ?,
            question_id = ?
        WHERE solutions.solution_id = ?;
        """;
    private final static String FIND_ALL_SQL = """
        SELECT solution_id,solution_text, solution_file, question_id, question_name,
               question_main_part, like_count, user_id, discipline_id
        FROM solutions
        """;
    private final static String FIND_SQL = FIND_ALL_SQL + """
        WHERE solution_id = ?
        """;
    @Override
    public Solution save(Solution solution) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, solution.getSolution());
            statement.setString(2, solution.getSolutionFile());
            statement.setLong(3, solution.getQuestion().getId());
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                solution.setId(keys.getLong("solution_id"));
            }
            return solution;
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
    public boolean update(Solution solution) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);
            statement.setString(1, solution.getSolution());
            statement.setString(2, solution.getSolutionFile());
            statement.setLong(3, solution.getQuestion().getId());
            statement.setLong(4, solution.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Solution findById(Long id) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_SQL);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return buildSolution(rs);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Solution> findAll() {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL);
            List<Solution> solutions = new ArrayList<>();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                solutions.add(buildSolution(rs));
            }
            return solutions;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private SolutionDao() {}

    public static SolutionDao getInstance() {
        return INSTANCE;
    }

    private Solution buildSolution(ResultSet rs) throws SQLException{
        Question question = new Question(
                rs.getLong("question_id"),
                rs.getString("question_name"),
                rs.getString("question_main_part"),
                rs.getInt("like_count"),
                null,
                null
        );
        return new Solution(
                rs.getLong("solution_id"),
                rs.getString("solution_text"),
                rs.getString("solution_file"),
                question
        );
    }
}
