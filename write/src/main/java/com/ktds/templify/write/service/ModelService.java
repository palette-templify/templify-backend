package com.ktds.templify.write.service;

import com.ktds.templify.write.dto.ModelResponse;
import com.ktds.templify.write.repository.AiModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelService {
    
    private final AiModelRepository aiModelRepository;
    
    public List<ModelResponse> getModels() {
        return null; // 구현 필요
    }
}
