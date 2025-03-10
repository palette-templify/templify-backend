package com.ktds.templify.write.service;

import com.ktds.templify.common.service.KafkaProducerService;
import com.ktds.templify.write.dto.*;
import com.ktds.templify.write.entity.Article;
import com.ktds.templify.write.entity.Template;
import com.ktds.templify.write.repository.ArticleRepository;
import com.ktds.templify.write.repository.TemplateRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class WriteService {

    private final ArticleRepository articleRepository;
    private final TemplateRepository templateRepository;
    private final KafkaProducerService kafkaProducerService;

    public ArticleResponse createArticle(Long userId, WriteRequest request) {
        Template template = templateRepository.findById(request.getTemplateId())
            .orElseThrow(() -> new EntityNotFoundException("Template not found"));

        // Article 객체 생성 및 저장
        Article article = Article.builder()
            .userId(userId)
            .title(request.getTitle())
            .content(request.getContent())
            .template(template)
            .build();
        article = articleRepository.save(article);

        // Transform 서비스 호출
        TransformRequest transformRequest = TransformRequest.builder()
            .userId(userId)
            .articleId(article.getId())
            .articleTitle(article.getTitle())
            .articleContent(request.getContent())
            .templateContent(template.getContent())
            .templateName(template.getName())
            .modelId(request.getModelId())
            .build();

        kafkaProducerService.sendMessage("write-topic", transformRequest);

        return ArticleResponse.builder()
            .id(article.getId())
            .title(request.getTitle())
            .originalContent(request.getContent())
            .templateName(template.getName())
            .build();
    }

    public void createTemplate(TemplateRequest request) {
        Template template = Template.from(request);
        templateRepository.save(template);
    }

    public List<TemplateOverviewResponse> getTemplates() {
        List<Template> templates = templateRepository.findAll();

        return templates.stream()
            .map(template -> new TemplateOverviewResponse(template.getId(), template.getName()))
            .collect(Collectors.toList());
    }

}
