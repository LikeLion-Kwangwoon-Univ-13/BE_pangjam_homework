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

            for (int i = 1; i < sheet.getLastRowNum(); i++) { // 10번째 줄부터
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String phone = getCellValue(row.getCell(3));
                String address = getCellValue(row.getCell(5));
                String category = getCellValue(row.getCell(1));
//                String category = categoryParts[0].trim();
                int distance = parseInt(getCellValue(row.getCell(2)));
                String name = getCellValue(row.getCell(4));
                double longitude = parseDouble(getCellValue(row.getCell(6)));
                double latitude = parseDouble(getCellValue(row.getCell(7)));
                String imageUrl = getCellValue(row.getCell(8));

                Place place = new Place(
                        name, address, phone, distance, latitude, longitude, category, imageUrl
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

