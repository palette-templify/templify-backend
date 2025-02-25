package com.ktds.templify.history.repository;

import com.ktds.templify.history.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByUserId(String userId);
    Optional<History> findByIdAndUserId(Long id, String userId);
}
