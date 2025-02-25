package com.ktds.templify.transform.service;

import com.ktds.templify.transform.dto.TransformRequest;
import com.ktds.templify.transform.dto.TransformResponse;
import com.ktds.templify.transform.dto.TransformStatus;
import com.ktds.templify.transform.entity.TransformRequestEntity;  // Entity 이름 변경
import com.ktds.templify.transform.repository.TransformRequestRepository;
import com.ktds.templify.transform.repository.TransformResultRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransformServiceTest {

    @Mock
    private TransformRequestRepository requestRepository;

    @Mock
    private TransformResultRepository resultRepository;

    @InjectMocks
    private TransformService transformService;

    @Test
    @DisplayName("변환 요청을 생성한다")
    void createTransformRequest() {
        // given
        String userId = "testUser";
        TransformRequest request = TransformRequest.builder()
            .articleId(1L)
            .modelId(1L)
            .templateId(1L)
            .build();

        given(requestRepository.save(any(TransformRequestEntity.class)))
            .willReturn(TransformRequestEntity.builder()
                .requestId("test-uuid")
                .articleId(1L)
                .userId(userId)
                .status("PENDING")
                .build());

        // when
        TransformResponse response = transformService.createTransformRequest(request, userId);

        // then
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo("PENDING");
        verify(requestRepository).save(any(TransformRequestEntity.class));
    }

    @Test
    @DisplayName("변환 상태를 조회한다")
    void getTransformStatus() {
        // given
        String requestId = "test-request-id";
        TransformRequestEntity transformRequest = TransformRequestEntity.builder()
            .requestId(requestId)
            .articleId(1L)
            .userId("testUser")
            .status("IN_PROGRESS")  // status 값 설정
            .build();

        given(requestRepository.findById(requestId))
            .willReturn(Optional.of(transformRequest));

        // when
        TransformStatus status = transformService.getStatus(requestId);

        // then
        assertThat(status).isNotNull();
        assertThat(status.getRequestId()).isEqualTo(requestId);
        assertThat(status.getStatus()).isEqualTo("IN_PROGRESS");
        assertThat(status.getProgress()).isEqualTo(50);  // IN_PROGRESS 상태일 때의 progress 값
    }
}