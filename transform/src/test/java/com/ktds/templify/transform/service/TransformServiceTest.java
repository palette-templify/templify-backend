//package com.ktds.templify.transform.service;
//
//import com.ktds.templify.transform.dto.TransformRequest;
//import com.ktds.templify.transform.dto.TransformResponse;
//import com.ktds.templify.transform.dto.TransformStatus;
//import com.ktds.templify.transform.entity.Transform;  // Entity 이름 변경
//import com.ktds.templify.transform.repository.TransformRequestRepository;
//import com.ktds.templify.transform.repository.TransformResultRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//class TransformServiceTest {
//
//    @Mock
//    private TransformRequestRepository requestRepository;
//
//    @Mock
//    private TransformResultRepository resultRepository;
//
//    @InjectMocks
//    private TransformService transformService;
//
//    @Test
//    @DisplayName("변환 요청을 생성한다")
//    void createTransformRequest() {
//        // given
//        String userId = "testUser";
//        TransformRequest request = TransformRequest.builder()
//            .userId(1L)
//            .articleId(1L)
//            .articleContent("content")
//            .templateId("abcde")
//            .modelId(1L)
//            .build();
//
//        given(requestRepository.save(any(Transform.class)))
//            .willReturn(Transform.builder()
//                .id(1L)
//                .articleId(1L)
//                .userId(1L)
//                .status("SUCCESS")
//                .build());
//
//        // when
//        TransformResponse response = transformService.transform(request);
//
//        // then
//        assertThat(response).isNotNull();
//        assertThat(response.getStatus()).isEqualTo("PENDING");
//        verify(requestRepository).save(any(Transform.class));
//    }
//
//}