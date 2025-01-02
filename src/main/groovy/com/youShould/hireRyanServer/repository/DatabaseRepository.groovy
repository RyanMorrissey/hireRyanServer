package com.youShould.hireRyanServer.repository

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// Don't really see myself using this outside of running some stored procedures?
// I am going to keep it in case I do need to use it later

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

