package com.ktds.templify.write.repository;

import com.ktds.templify.write.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<Template, Long> {
}
