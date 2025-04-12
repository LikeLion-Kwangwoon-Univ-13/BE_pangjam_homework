package com.likelionweek4.homework.utils.exportexcel;

import com.likelionweek4.homework.entity.Place;
import com.likelionweek4.homework.repository.place.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ExcelExporter {

    private final PlaceRepository placeRepository;

    public void importFromClasspath() throws IOException {
        ClassPathResource resource = new ClassPathResource("excel/location.xlsx");
        try (Workbook workbook = WorkbookFactory.create(resource.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 10; i <= sheet.getLastRowNum(); i++) { // 10번째 줄부터
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String phone = getCellValue(row.getCell(5));
                String address = getCellValue(row.getCell(8));
                String categoryGroup = getCellValue(row.getCell(2));
                String[] categoryParts = getCellValue(row.getCell(3)).split(">");
                String category = categoryParts[categoryParts.length - 1].trim();
                int distance = parseInt(getCellValue(row.getCell(4)));
                String name = getCellValue(row.getCell(6));
                double longitude = parseDouble(getCellValue(row.getCell(9)));
                double latitude = parseDouble(getCellValue(row.getCell(10)));
                System.out.println("이름: " + name); // 이때 이미 깨지면 Excel 파싱 문제


                Place place = new Place(
                        name, address, phone, distance, latitude, longitude, categoryGroup, category
                );

                placeRepository.save(place);
            }
        }
    }

    private String getCellValue(Cell cell) {
        return (cell == null) ? "" : cell.toString().trim();
    }

    private int parseInt(String val) {
        try { return Integer.parseInt(val); } catch (Exception e) { return 0; }
    }

    private double parseDouble(String val) {
        try { return Double.parseDouble(val); } catch (Exception e) { return 0.0; }
    }
}

