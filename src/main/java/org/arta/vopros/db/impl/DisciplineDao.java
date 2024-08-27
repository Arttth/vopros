package org.arta.vopros.db.impl;

import org.arta.vopros.db.Dao;
import org.arta.vopros.domain.Discipline;
import org.arta.vopros.exception.DAOException;
import org.arta.vopros.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplineDao implements Dao<Discipline, Long> {
    private final static DisciplineDao INSTANCE = new DisciplineDao();
    private final static String SAVE_SQL = """
            INSERT INTO disciplines (discipline_name, parent_discipline_id)
            VALUES (?, ?);
            """;
    private final static String DELETE_SQL = """
            DELETE FROM disciplines
            WHERE discipline_id = ?;
        """;
    private final static String UPDATE_SQL = """
            UPDATE disciplines
            SET discipline_name = ?,
                parent_discipline_id = ?
            where discipline_id = ?;
            """;
    private final static String FIND_ALL_SQL = """
            SELECT discipline_id, discipline_name, parent_discipline_id
            FROM disciplines
            """;
    private final static String FIND_SQL = FIND_ALL_SQL + """
        WHERE discipline_id = ?;
        """;
    @Override
    public Discipline save(Discipline discipline) {
        try (Connection connection = ConnectionManager.getConnection()){
            PreparedStatement statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, discipline.getName());
            statement.setLong(2, discipline.getParentDiscipline().getId());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                discipline.setId(rs.getLong("discipline_id"));
            }
            return discipline;
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
    public boolean update(Discipline discipline) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);
            statement.setString(1, discipline.getName());
            statement.setLong(2, discipline.getParentDiscipline().getId());
            statement.setLong(3, discipline.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Discipline findById(Long id) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_SQL);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return buildDiscipline(rs);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Discipline> findAll() {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL);
            List<Discipline> disciplines = new ArrayList<>();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                disciplines.add(buildDiscipline(rs));
            }
            return disciplines;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public DisciplineDao getInstance() {
        return INSTANCE;
    }
    private DisciplineDao() {}
    private Discipline buildDiscipline(ResultSet rs) throws SQLException{
        Discipline parentDiscipline = new Discipline(
                rs.getLong("parent_discipline_id"),
                null,
                null);
        return new Discipline(
                rs.getLong("discipline_id"),
                rs.getString("discipline_name"),
                parentDiscipline
        );
    }
}
