package com.jaegarsaun.finance.repository;

import java.util.List;

import java.util.List;
import java.util.Map;

public interface AccountAuditRepository {
    List<Map<String, Object>> findByUserId(int userId);
}


