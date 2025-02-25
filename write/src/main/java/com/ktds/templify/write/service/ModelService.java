package com.ktds.templify.write.service;

import com.ktds.templify.write.dto.ModelResponse;
import com.ktds.templify.write.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelService {
    
    private final ModelRepository modelRepository;
    
    public List<ModelResponse> getModels() {
        return null; // 구현 필요
    }
}
