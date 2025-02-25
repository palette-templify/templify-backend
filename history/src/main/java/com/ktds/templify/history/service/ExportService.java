package com.ktds.templify.history.service;

import com.ktds.templify.history.entity.History;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExportService {
    
    public byte[] exportToPdf(History history) {
        // PDF 변환 로직
        return new byte[0];
    }
    
    public byte[] exportToWord(History history) {
        // Word 변환 로직
        return new byte[0];
    }
    
    public byte[] exportToText(History history) {
        // Text 변환 로직
        return history.getTransformedText().getBytes();
    }
}
