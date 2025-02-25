package com.ktds.templify.transform.entity;

import com.ktds.templify.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transform_results")
@Getter
@NoArgsConstructor
public class TransformResultEntity extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String requestId;
    
    @Column(nullable = false)
    private Long articleId;
    
    @Column(nullable = false)
    private String userId;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String originalText;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String transformedText;
    
    private Long templateId;
    
    private Long modelId;
    
    private Integer tokenCount;
    
    private Integer processingTime;
}
