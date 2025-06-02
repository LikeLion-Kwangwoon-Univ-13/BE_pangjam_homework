package com.likelionweek4.homework.dto.review;

import com.likelionweek4.homework.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {

    @Setter
    @Getter
    @NoArgsConstructor
    public static class CreateReviewDTO {
        private Long reviewId;
        private int rating;
        private String comment;
        private LocalDateTime createdAt;

        public CreateReviewDTO(Review review) {
            this.reviewId = review.getId();
            this.rating = review.getRating();
            this.comment = review.getComment();
            this.createdAt = review.getCreatedAt();
        }
    }

    @Setter
    @Getter
    @NoArgsConstructor
    public static class SearchReviewDTO {
        Page<CreateReviewDTO> searchedReviews;

        public SearchReviewDTO(Page<Review> reviews) {
            searchedReviews = reviews.map(CreateReviewDTO::new);
        }
    }
}
