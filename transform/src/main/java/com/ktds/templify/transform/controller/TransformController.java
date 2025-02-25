package com.ktds.templify.transform.controller;

import com.ktds.templify.common.dto.ApiResponse;
import com.ktds.templify.transform.dto.TransformRequest;
import com.ktds.templify.transform.dto.TransformResponse;
import com.ktds.templify.transform.dto.TransformStatus;
import com.ktds.templify.transform.service.TransformService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "글 변환", description = "글 변환 관련 API")
@RestController
@RequestMapping("/api/transform")
@RequiredArgsConstructor
public class TransformController {

    private final TransformService transformService;

    @Operation(summary = "변환 요청", description = "글 변환을 요청합니다.")
    @PostMapping("/request")
    public ApiResponse<TransformResponse> requestTransform(@RequestBody TransformRequest request) {
        return ApiResponse.success(transformService.createTransformRequest(request, "userId"));
    }

    @Operation(summary = "변환 상태 조회", description = "변환 요청의 현재 상태를 조회합니다.")
    @GetMapping("/status/{requestId}")
    public ApiResponse<TransformStatus> getTransformStatus(@PathVariable String requestId) {
        return ApiResponse.success(transformService.getStatus(requestId));
    }
}
