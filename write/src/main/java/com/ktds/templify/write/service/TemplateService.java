package com.ktds.templify.write.service;

import com.ktds.templify.write.dto.TemplateResponse;
import com.ktds.templify.write.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TemplateService {
    
    private final TemplateRepository templateRepository;
    
    public List<TemplateResponse> getTemplates() {
        return null; // 구현 필요
    }
}
