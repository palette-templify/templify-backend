package com.ktds.templify.transform.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ChatGptResponseDto {

    @JsonProperty("choices")
    private List<Choice> choices;

    @JsonProperty("usage")
    private Usage usage;

    @Data
    public static class Choice {
        private Message message;
    }

    @Data
    public static class Message {
        private String content;
    }

    @Data
    public static class Usage {
        @JsonProperty("prompt_tokens")
        private int promptTokens;

        @JsonProperty("completion_tokens")
        private int completionTokens;

        @JsonProperty("total_tokens")
        private int totalTokens;
    }

    public String getContent() {
        return choices != null && !choices.isEmpty() ? choices.get(0).getMessage().getContent() : "";
    }

    public int getTotalTokenUsage() {
        return usage != null ? usage.getTotalTokens() : 0;
    }
}