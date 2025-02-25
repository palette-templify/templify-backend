package com.ktds.templify.write.repository;

import com.ktds.templify.write.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriteRepository extends JpaRepository<Article, Long> {
}
