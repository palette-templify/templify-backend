package com.ktds.templify.history.repository;

import com.ktds.templify.history.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByUserId(Long userId);
    Optional<History> findByIdAndUserId(Long id, Long userId);
    Optional<History> findByArticleId(Long articleId);

}
