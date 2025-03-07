package com.ktds.templify.write.entity;

import com.ktds.templify.write.dto.TemplateRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "templates")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Template {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String content;

    public static Template from(TemplateRequest request) {
        return Template.builder()
            .name(request.getName())
            .content(request.getContent())
            .build();
    }
}
