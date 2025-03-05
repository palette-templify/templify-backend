package com.ktds.templify.write.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.ktds.templify.write.dto.WriteRequest;
import com.ktds.templify.write.entity.Article;
import com.ktds.templify.write.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WriteServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private WriteService writeService;

    @Test
    @DisplayName("글 작성 요청을 처리한다")
    void createArticle() {
        // given
        Long userId = 1L;
        WriteRequest request = new WriteRequest();
        request.setTitle("Test Title");
        request.setContent("Test Content");
        request.setTemplateId(1L);
        request.setModelId(1L);

        Article savedArticle = new Article(); // 필요한 필드 설정
        given(articleRepository.save(any(Article.class))).willReturn(savedArticle);

        // when
        writeService.createArticle(userId, request);

        // then
        verify(articleRepository).save(any(Article.class));
    }
}
