package com.ktds.templify.transform.service;

import com.ktds.templify.transform.dto.TransformRequest;
import com.ktds.templify.transform.dto.TransformResponse;
import com.ktds.templify.transform.entity.Transform;
import com.ktds.templify.transform.repository.TransformRequestRepository;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransformService {

    private final ChatGptService chatGptService;
    private final TransformRequestRepository requestRepository;

    public TransformResponse transform(TransformRequest request) {
        String transformedContent;
        transformedContent = chatGptService.transformContent(
            request.getArticleContent(),
            request.getTemplateId()
        );

        // history 저장

        Transform transform = Transform.builder()
            .articleId(request.getArticleId())
            .userId(request.getUserId())
            .status("SUCCESS")
            .build();
        requestRepository.save(transform);

        return TransformResponse.builder()
            .transformedContent(transformedContent)
            .build();
    }

}
