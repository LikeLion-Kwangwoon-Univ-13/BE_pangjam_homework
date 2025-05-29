package com.example.pangjam.controller;

import com.example.pangjam.dto.PlaceInfoDto;
import com.example.pangjam.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/places")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @GetMapping("/page/{page}")
    public ResponseEntity<Map<String, Object>> getPlaces(
            @PathVariable int page,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String name) {
        int adjustedPage = page - 1;

        Page<PlaceInfoDto> placesPage = placeService.getPlaces(adjustedPage, category, name);
        List<PlaceInfoDto> placeInfos = placesPage.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("placeInfos", placeInfos);

        Map<String, Object> pageableInfo = new HashMap<>();
        pageableInfo.put("page", placesPage.getNumber() + 1);
        pageableInfo.put("size", placesPage.getSize());
        pageableInfo.put("hasNext", placesPage.hasNext());
        response.put("pageable", pageableInfo);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{placeId}")
    public ResponseEntity<PlaceInfoDto> getPlace(@PathVariable Long placeId) {
        PlaceInfoDto place = placeService.getPlace(placeId);
        return new ResponseEntity<>(place, HttpStatus.OK);
    }
}