package com.ktds.templify.write.repository;

import com.ktds.templify.write.entity.AiModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<AiModel, Long> {
}
