package com.likelionweek4.homework.dto.review;

import com.likelionweek4.homework.entity.Review;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

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

    @Setter
    @NoArgsConstructor
    public static class SearchReviewDTO {
        Page<CreateReviewDTO> searchedReviews;

        public SearchReviewDTO(Page<Review> reviews) {
            searchedReviews = reviews.map(review -> new CreateReviewDTO());
        }
    }
}
