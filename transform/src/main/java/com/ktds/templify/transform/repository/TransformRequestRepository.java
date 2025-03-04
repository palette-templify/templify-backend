package com.ktds.templify.transform.repository;

import com.ktds.templify.transform.entity.Transform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransformRequestRepository extends JpaRepository<Transform, String> {
}
