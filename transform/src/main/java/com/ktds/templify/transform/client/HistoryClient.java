package com.ktds.templify.transform.client;

import com.ktds.templify.transform.dto.HistoryRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "history-client", url = "${history.api.url}")
public interface HistoryClient {

    @PostMapping("/api/history")
    ResponseEntity<Void> createHistory(@RequestBody HistoryRequest request);
}