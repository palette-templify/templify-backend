package com.ktds.templify.transform.controller;

import com.ktds.templify.common.dto.ApiResponse;
import com.ktds.templify.transform.dto.TransformRequest;
import com.ktds.templify.transform.dto.TransformResponse;
import com.ktds.templify.transform.service.ChatGptService;
import com.ktds.templify.transform.service.TransformService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "글 변환", description = "글 변환 관련 API")
@RestController
@RequestMapping("/api/transform")
@RequiredArgsConstructor
public class TransformController {

    private final TransformService transformService;
    private final ChatGptService chatGptService;

    @Operation(summary = "변환 요청", description = "글 변환을 요청합니다.")
    @PostMapping("/request")
    public ApiResponse<TransformResponse> requestTransform(@RequestBody TransformRequest request) {
        return ApiResponse.success(transformService.transform(request));
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<String>> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(
                ApiResponse.error(400, "파일이 비어있습니다.")
            );
        }

        String fileId = chatGptService.uploadFile(file);
        if (fileId == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ApiResponse.error(500, "파일 업로드에 실패했습니다.")
            );
        }

        return ResponseEntity.ok(ApiResponse.success(fileId));
    }

}
