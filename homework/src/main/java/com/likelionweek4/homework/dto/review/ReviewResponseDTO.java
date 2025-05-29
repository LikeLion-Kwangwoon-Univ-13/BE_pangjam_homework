package com.likelionweek4.homework.dto.review;

import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class ReviewResponseDTO {

    @Setter
    @NoArgsConstructor
    public static class CreateReviewDTO {
        private Long reviewId;
        private int rating;
        private String comment;
        private LocalDateTime createdAt;

        public CreateReviewDTO(Long reviewId, int rating, String comment, LocalDateTime createdAt) {
            this.reviewId = reviewId;
            this.rating = rating;
            this.comment = comment;
            this.createdAt = createdAt;
        }
    }
}
