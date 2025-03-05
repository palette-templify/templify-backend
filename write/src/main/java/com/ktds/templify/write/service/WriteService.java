package com.ktds.templify.write.service;

import com.ktds.templify.write.client.TransformClient;
import com.ktds.templify.write.dto.ArticleResponse;
import com.ktds.templify.write.dto.TemplateRequest;
import com.ktds.templify.write.dto.TransformRequest;
import com.ktds.templify.write.dto.TransformResponse;
import com.ktds.templify.write.dto.WriteRequest;
import com.ktds.templify.write.entity.Article;
import com.ktds.templify.write.entity.Template;
import com.ktds.templify.write.repository.ArticleRepository;
import com.ktds.templify.write.repository.TemplateRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WriteService {
    
    private final ArticleRepository articleRepository;
    private final TemplateRepository templateRepository;
//    private final AiModelRepository aiModelRepository;

    private final TransformClient transformClient;

    @Transactional
    public ArticleResponse createArticle(Long userId, WriteRequest request) {
        Template template = templateRepository.findById(request.getTemplateId())
            .orElseThrow(() -> new EntityNotFoundException("Template not found"));
//        AiModel aiModel = aiModelRepository.findById(request.getModelId())
//            .orElseThrow(() -> new EntityNotFoundException("AiModel not found"));

        // Article 객체 생성 및 저장
        Article article = Article.builder()
            .userId(userId)
            .title(request.getTitle())
            .content(request.getContent())
            .template(template)
//            .model(aiModel)
            .build();
        article = articleRepository.save(article);

        // Transform 서비스 호출
        TransformRequest transformRequest = TransformRequest.builder()
            .userId(userId)
            .articleId(article.getId())
            .articleContent(request.getContent())
            .templateContent(template.getContent())
            .templateName(template.getName())
            .modelId(request.getModelId())
            .build();
        TransformResponse transformResponseApiResponse = transformClient.requestTransform(
            transformRequest).getData();

        return ArticleResponse.builder()
            .originalContent(request.getContent())
            .transformedContent(transformResponseApiResponse.getTransformedContent())
            .templateName(template.getName())
            .build();
    }

    public void createTemplate(TemplateRequest request) {
        Template template = Template.from(request);
        templateRepository.save(template);
    }
}
