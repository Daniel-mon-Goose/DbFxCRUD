package ru.nsu.protasov.repository;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class BasicRepositoryImpl implements BasicRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Map<String, Object>> executeSQL(String query, Pageable page) throws DataAccessException {
        query = query.replaceAll(";$", "");
        return jdbcTemplate.queryForList(query
                + " LIMIT " + page.getPageSize()
                + " OFFSET " + page.getOffset());
    }

    public List<Map<String, Object>> executeStraight(String query) throws PSQLException {
        return jdbcTemplate.queryForList(query);
    }

    public void execute(String query) {
        jdbcTemplate.execute(query);
    }
}
