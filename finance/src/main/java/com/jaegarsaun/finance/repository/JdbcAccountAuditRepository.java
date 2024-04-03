package com.jaegarsaun.finance.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcAccountAuditRepository implements AccountAuditRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> findByUserId(int userId) {
        String sql = "SELECT a.*, r.REVTSTMP FROM Account_AUD a " +
                "JOIN REVINFO r ON a.REV = r.REV " +
                "WHERE a.userId = ?";
        return jdbcTemplate.queryForList(sql, userId);
    }
}


