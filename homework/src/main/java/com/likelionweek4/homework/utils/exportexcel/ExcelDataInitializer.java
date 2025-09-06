package com.likelionweek4.homework.utils.exportexcel;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ExcelDataInitializer {

    private final ExcelExporter excelExporter;

    @PostConstruct
    public void init() {
        try {
            excelExporter.importFromClasspath();
            System.out.println("📊 Excel 데이터 초기화 완료!");
        } catch (IOException e) {
            System.err.println("❌ Excel 데이터 초기화 실패: " + e.getMessage());
        }
    }
}

