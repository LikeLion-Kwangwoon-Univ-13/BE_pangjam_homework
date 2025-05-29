package com.example.pangjam.controller;

import com.example.pangjam.dto.PlaceReviewCreateDto;
import com.example.pangjam.dto.PlaceReviewInfoDto;
import com.example.pangjam.service.PlaceReviewService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/placeReviews")
public class PlaceReviewController {

    @Autowired
    private PlaceReviewService placeReviewService;

    @PostMapping("/{placeId}")
    public ResponseEntity<PlaceReviewInfoDto> createPlaceReview(
            @PathVariable Long placeId,
            @RequestBody PlaceReviewCreateDto createDto) {
        PlaceReviewInfoDto placeReview = placeReviewService.createPlaceReview(placeId, createDto);
        return new ResponseEntity<>(placeReview, HttpStatus.CREATED);
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<Map<String, Object>> getPlaceReviews(
            @PathVariable int page,
            @RequestParam Long placeId,
            @RequestParam(defaultValue = "최신순") String sortBy) {
        Page<PlaceReviewInfoDto> placeReviewsPage = placeReviewService.getPlaceReviews(page, placeId, sortBy);
        List<PlaceReviewInfoDto> placeReviewInfos = placeReviewsPage.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("placeReviewInfos", placeReviewInfos);

        Map<String, Object> pageableInfo = new HashMap<>();
        pageableInfo.put("page", placeReviewsPage.getNumber() + 1);
        pageableInfo.put("size", placeReviewsPage.getSize());
        pageableInfo.put("hasNext", placeReviewsPage.hasNext());
        response.put("pageable", pageableInfo);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}