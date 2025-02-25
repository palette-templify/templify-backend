package com.ktds.templify.write.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class SpellCheckResponse {
    private String originalText;
    private String correctedText;
    private List<String> corrections;
}
