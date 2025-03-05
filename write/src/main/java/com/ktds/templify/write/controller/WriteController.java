package com.ktds.templify.write.controller;

import com.ktds.templify.common.dto.ApiResponse;
import com.ktds.templify.write.dto.ArticleResponse;
import com.ktds.templify.write.dto.TemplateRequest;
import com.ktds.templify.write.dto.WriteRequest;
import com.ktds.templify.write.service.WriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "글 저작", description = "글 저작 관련 API")
@RestController
@RequestMapping("/api/write")
@RequiredArgsConstructor
public class WriteController {

    private final WriteService writeService;

    @Operation(summary = "글 작성", description = "새로운 글을 작성합니다.")
    @PostMapping("/article")
    public ApiResponse<ArticleResponse> createArticle(@RequestHeader("x-user-id") Long userId, @RequestBody WriteRequest request) {
        return ApiResponse.success(writeService.createArticle(userId, request));
    }

    @Operation(summary = "템플릿 생성", description = "새로운 템플릿을 작성합니다.")
    @PostMapping("/template")
    public ApiResponse<ArticleResponse> createTemplate(@RequestBody TemplateRequest request) {
        writeService.createTemplate(request);
        return ApiResponse.success(null);
    }

}
