package com.ktds.templify.write.service;

import com.ktds.templify.write.dto.ArticleRequest;
import com.ktds.templify.write.dto.ArticleResponse;
import com.ktds.templify.write.entity.Article;
import com.ktds.templify.write.repository.WriteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WriteServiceTest {

    @Mock
    private WriteRepository writeRepository;

    @Mock
    private SpellCheckService spellCheckService;

    @InjectMocks
    private WriteService writeService;

    @Test
    @DisplayName("글 작성 요청을 처리한다")
    void createArticle() {
        // given
        String userId = "testUser";
        ArticleRequest request = new ArticleRequest();
        request.setTitle("Test Title");
        request.setContent("Test Content");
        request.setTemplateId(1L);
        request.setModelId(1L);

        Article savedArticle = new Article(); // 필요한 필드 설정
        given(writeRepository.save(any(Article.class))).willReturn(savedArticle);

        // when
        ArticleResponse response = writeService.createArticle(request, userId);

        // then
        assertThat(response).isNotNull();
        verify(writeRepository).save(any(Article.class));
    }
}
