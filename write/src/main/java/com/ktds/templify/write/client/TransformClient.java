package com.ktds.templify.write.client;

import com.ktds.templify.common.dto.ApiResponse;
import com.ktds.templify.write.dto.TransformRequest;
import com.ktds.templify.write.dto.TransformResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "transform-client", url = "${transform.api.url}")
public interface TransformClient {
    @PostMapping("/api/transform/request")
    ApiResponse<TransformResponse> requestTransform(@RequestBody TransformRequest request);
}