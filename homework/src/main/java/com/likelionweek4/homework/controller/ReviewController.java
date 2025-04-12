package com.likelionweek4.homework.controller;

import com.likelionweek4.homework.dto.MessageResponseDTO;
import com.likelionweek4.homework.dto.review.ReviewRequestDTO;
import com.likelionweek4.homework.dto.review.ReviewResponseDTO;
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
    public ResponseEntity<ReviewResponseDTO.ReviewInfo> createReview(
            @RequestBody ReviewRequestDTO.CreateReviewInfo requestDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(reviewService.create(requestDTO));
    }

    @GetMapping("/{placeId}")
    public ResponseEntity<ReviewResponseDTO.SearchReviewsResult> searchReviews(
            @PathVariable Long placeId,
            @RequestParam(defaultValue = "latest") String sortBy) {
        ReviewRequestDTO.SearchReviewsInfo requestDTO = new ReviewRequestDTO.SearchReviewsInfo(placeId, sortBy);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(reviewService.searchReviewByPlaceId(requestDTO));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDTO.ReviewInfo> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewRequestDTO.UpdateReviewInfo requestDTO) {
        requestDTO.setReviewId(reviewId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(reviewService.update(requestDTO));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<MessageResponseDTO.Message> deleteReview(
            @PathVariable Long reviewId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(reviewService.deleteReview(new ReviewRequestDTO.DeleteReviewInfo(reviewId)));
    }

}
