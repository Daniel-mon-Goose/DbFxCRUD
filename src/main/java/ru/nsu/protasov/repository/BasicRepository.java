package ru.nsu.protasov.repository;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BasicRepository {
    public List<Map<String, Object>> executeSQL(String query, Pageable page);
}
