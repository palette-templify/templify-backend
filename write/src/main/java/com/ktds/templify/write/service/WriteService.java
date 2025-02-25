package com.ktds.templify.write.service;

import com.ktds.templify.write.dto.ArticleRequest;
import com.ktds.templify.write.dto.ArticleResponse;
import com.ktds.templify.write.repository.WriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WriteService {
    
    private final WriteRepository writeRepository;
    private final SpellCheckService spellCheckService;
    
    @Transactional
    public ArticleResponse createArticle(ArticleRequest request, String userId) {
        // 글 작성 로직
        return null; // 구현 필요
    }
}
