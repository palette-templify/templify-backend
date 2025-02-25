package com.ktds.templify.transform.entity;

import com.ktds.templify.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transform_requests")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransformRequestEntity extends BaseEntity {

    @Id
    private String requestId;

    @Column(nullable = false)
    private Long articleId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String status;

    @Column(columnDefinition = "TEXT")
    private String errorMessage;
}