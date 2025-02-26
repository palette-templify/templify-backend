package com.ktds.templify.history.controller;

import com.ktds.templify.common.dto.ApiResponse;
import com.ktds.templify.history.dto.HistoryDetailResponse;
import com.ktds.templify.history.dto.HistoryRequest;
import com.ktds.templify.history.dto.HistoryResponse;
import com.ktds.templify.history.service.HistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "히스토리", description = "변환 히스토리 관련 API")
@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @Operation(summary = "히스토리 생성(저장)", description = "변환 히스토리를 저장합니다.")
    @PostMapping
    public ApiResponse<Void> createHistory(@RequestBody HistoryRequest request) {

        historyService.createHistory(request);
        return ApiResponse.success(null);
    }

    @Operation(summary = "히스토리 목록 조회", description = "변환 히스토리 목록을 조회합니다.")
    @GetMapping("/list")
    public ApiResponse<List<HistoryResponse>> getHistoryList() {
        return ApiResponse.success(historyService.getHistories("userId"));
    }

    @Operation(summary = "히스토리 상세 조회", description = "특정 변환 히스토리의 상세 정보를 조회합니다.")
    @GetMapping("/{id}")
    public ApiResponse<HistoryDetailResponse> getHistory(@PathVariable Long id) {
        return ApiResponse.success(historyService.getHistory(id, "userId"));
    }

}
