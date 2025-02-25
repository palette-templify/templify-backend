package com.ktds.templify.transform.repository;

import com.ktds.templify.transform.entity.TransformResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransformResultRepository extends JpaRepository<TransformResultEntity, Long> {
    TransformResultEntity findByRequestId(String requestId);
}
