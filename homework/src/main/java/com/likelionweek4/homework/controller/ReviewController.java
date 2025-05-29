package com.likelionweek4.homework.controller;

import com.likelionweek4.homework.dto.review.ReviewRequestDTO;
import com.likelionweek4.homework.dto.review.ReviewResponseDTO;
import com.likelionweek4.homework.entity.Review;
import com.likelionweek4.homework.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponseDTO.CreateReviewDTO> createReview(
            @ModelAttribute ReviewRequestDTO.CreateReviewDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.createReview(requestDTO));
    }
}
