package com.likelionweek4.homework.controller;

import com.likelionweek4.homework.dto.review.ReviewRequestDTO;
import com.likelionweek4.homework.dto.review.ReviewResponseDTO;
import com.likelionweek4.homework.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponseDTO.CreateReviewDTO> createReview(
            @RequestBody ReviewRequestDTO.CreateReviewDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.createReview(requestDTO));
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<ReviewResponseDTO.SearchReviewDTO> searchReview(
            @PathVariable int page) {
        ReviewRequestDTO.SearchReviewDTO requestDTO = new ReviewRequestDTO.SearchReviewDTO();
        requestDTO.setPage(page);
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.searchReview(requestDTO));
    }
}
