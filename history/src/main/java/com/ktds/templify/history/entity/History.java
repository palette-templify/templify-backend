package com.ktds.templify.history.entity;

import com.ktds.templify.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "history")
@Getter
@NoArgsConstructor
@SuperBuilder
public class History extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String requestId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String templateName;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String originalText;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String transformedText;

    private String modelName;

    private Integer tokenCount;

    private Integer processingTime;

}



