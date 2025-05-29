package com.example.pangjam.controller;

import com.example.pangjam.dto.ReviewCreateDto;
import com.example.pangjam.dto.ReviewInfoDto;
import com.example.pangjam.service.ReviewService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewInfoDto> createReview(
            @RequestBody ReviewCreateDto createDto) {
        ReviewInfoDto review = reviewService.createReview(createDto);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<Map<String, Object>> getReviews(
            @PathVariable int page) {
        Page<ReviewInfoDto> reviewsPage = reviewService.getReviews(page);
        List<ReviewInfoDto> reviewInfos = reviewsPage.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("reviewInfos", reviewInfos);

        Map<String, Object> pageableInfo = new HashMap<>();
        pageableInfo.put("page", reviewsPage.getNumber() + 1);
        pageableInfo.put("size", reviewsPage.getSize());
        pageableInfo.put("hasNext", reviewsPage.hasNext());
        response.put("pageable", pageableInfo);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}