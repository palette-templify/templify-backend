package com.ktds.templify.history.entity;

import com.ktds.templify.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "transform_results")
@Getter
@NoArgsConstructor
public class History extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String requestId;
    
    @Column(nullable = false)
    private String userId;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String originalText;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String transformedText;
    
    private String templateName;
    
    private String modelName;
    
    private Integer tokenCount;
    
    private Integer processingTime;
    
    private LocalDateTime transformedAt;
}
