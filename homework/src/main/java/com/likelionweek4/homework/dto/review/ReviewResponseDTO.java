package com.likelionweek4.homework.dto.review;

import com.likelionweek4.homework.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        List<CreateReviewDTO> searchedReviews;
        Boolean last;
        public SearchReviewDTO(Page<Review> reviews) {
            this.searchedReviews = reviews.stream()
                    .map(CreateReviewDTO::new)
                    .collect(Collectors.toList());
            this.last = reviews.isLast();
        }
    }
}
