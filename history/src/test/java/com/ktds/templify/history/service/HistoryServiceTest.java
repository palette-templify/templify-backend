package com.ktds.templify.history.service;

import com.ktds.templify.history.dto.HistoryResponse;
import com.ktds.templify.history.dto.HistoryDetailResponse;
import com.ktds.templify.history.entity.History;
import com.ktds.templify.history.repository.HistoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HistoryServiceTest {

    @Mock
    private HistoryRepository historyRepository;

    @InjectMocks
    private HistoryService historyService;

    @Test
    @DisplayName("사용자의 히스토리 목록을 조회한다")
    void getHistories() {
        // given
        Long userId = 1L;
        List<History> histories = List.of(new History()); // 테스트용 히스토리 객체 생성
        given(historyRepository.findByUserId(userId)).willReturn(histories);

        // when
        List<HistoryResponse> responses = historyService.getHistories(userId);

        // then
        assertThat(responses).isNotNull();
        assertThat(responses).hasSize(1);
        verify(historyRepository).findByUserId(userId);
    }

    @Test
    @DisplayName("히스토리 상세 정보를 조회한다")
    void getHistory() {
        // given
        Long historyId = 1L;
        Long userId = 1L;
        History history = new History(); // 테스트용 히스토리 객체 생성
        given(historyRepository.findByIdAndUserId(historyId, userId))
            .willReturn(Optional.of(history));

        // when
        HistoryDetailResponse response = historyService.getHistory(historyId, userId);

        // then
        assertThat(response).isNotNull();
        verify(historyRepository).findByIdAndUserId(historyId, userId);
    }
}
