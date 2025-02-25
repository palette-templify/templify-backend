package com.ktds.templify.history.controller;

import com.ktds.templify.common.dto.ApiResponse;
import com.ktds.templify.history.dto.ExportRequest;
import com.ktds.templify.history.dto.HistoryResponse;
import com.ktds.templify.history.dto.HistoryDetailResponse;
import com.ktds.templify.history.service.HistoryService;
import com.ktds.templify.history.service.ExportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "히스토리", description = "변환 히스토리 관련 API")
@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;
    private final ExportService exportService;

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

    @Operation(summary = "히스토리 내보내기", description = "히스토리를 지정된 형식으로 내보냅니다.")
    @PostMapping("/{id}/export")
    public ResponseEntity<byte[]> exportHistory(
        @PathVariable Long id,
        @RequestBody ExportRequest request
    ) {
        byte[] content = new byte[0];
        String filename = String.format("history_%d.%s", id, request.getFormat().toLowerCase());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", filename);

        return ResponseEntity.ok()
            .headers(headers)
            .body(content);
    }
}
