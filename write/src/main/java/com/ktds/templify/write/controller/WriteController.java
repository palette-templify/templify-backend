package com.ktds.templify.write.controller;

import com.ktds.templify.common.dto.ApiResponse;
import com.ktds.templify.write.dto.*;
import com.ktds.templify.write.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "글 저작", description = "글 저작 관련 API")
@RestController
@RequestMapping("/api/write")
@RequiredArgsConstructor
public class WriteController {

    private final WriteService writeService;
    private final TemplateService templateService;
    private final ModelService modelService;
    private final SpellCheckService spellCheckService;

    @Operation(summary = "글 작성", description = "새로운 글을 작성합니다.")
    @PostMapping("/article")
    public ApiResponse<ArticleResponse> createArticle(@RequestBody ArticleRequest request) {
        return ApiResponse.success(writeService.createArticle(request, "userId"));
    }

    @Operation(summary = "템플릿 목록 조회", description = "사용 가능한 템플릿 목록을 조회합니다.")
    @GetMapping("/templates")
    public ApiResponse<List<TemplateResponse>> getTemplates() {
        return ApiResponse.success(templateService.getTemplates());
    }

    @Operation(summary = "AI 모델 목록 조회", description = "사용 가능한 AI 모델 목록을 조회합니다.")
    @GetMapping("/models")
    public ApiResponse<List<ModelResponse>> getModels() {
        return ApiResponse.success(modelService.getModels());
    }

    @Operation(summary = "맞춤법 검사", description = "입력된 텍스트의 맞춤법을 검사합니다.")
    @PostMapping("/spell-check")
    public ApiResponse<SpellCheckResponse> checkSpelling(@RequestBody String text) {
        return ApiResponse.success(spellCheckService.check(text));
    }
}
