package com.youShould.hireRyanServer.repository

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// Don't really see myself using this outside of running some stored procedures?

@Repository
class DatabaseRepository {

    private final JdbcTemplate jdbcTemplate;

    DatabaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    List<Map<String, Object>> getAllDatabaseTests() {
        String sql = "SELECT * FROM database_test";
        return jdbcTemplate.queryForList(sql);
    }
}

