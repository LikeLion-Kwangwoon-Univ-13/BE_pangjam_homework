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
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponseDTO.ReviewInfo> createReview(
            @RequestBody ReviewRequestDTO.CreateReviewInfo requestDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ReviewResponseDTO.ReviewInfo(reviewService.create(requestDTO)));
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<ReviewResponseDTO.SearchReviewsResult> searchReviews(
            @PathVariable Long restaurantId ) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ReviewResponseDTO.SearchReviewsResult(reviewService.searchReviewByRestaurantId(new ReviewRequestDTO.SearchReviewsInfo(restaurantId))));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDTO.ReviewInfo> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewRequestDTO.UpdateReviewInfo requestDTO) {
        requestDTO.setReviewId(reviewId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ReviewResponseDTO.ReviewInfo(reviewService.update(requestDTO)));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<MessageResponseDTO.Message> deleteReview(
            @PathVariable Long reviewId) {
        reviewService.deleteReview(new ReviewRequestDTO.DeleteReviewInfo(reviewId));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessageResponseDTO.Message("삭제가 완료되었습니다."));
    }

}
