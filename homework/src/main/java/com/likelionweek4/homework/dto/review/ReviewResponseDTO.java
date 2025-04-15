package com.likelionweek4.homework.dto.review;

import com.likelionweek4.homework.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import java.time.LocalDateTime;

public class ReviewResponseDTO {


    @Getter
    @NoArgsConstructor
    public static class ReviewInfo {
        private Long id;
        private int rating;
        private String comment;
        private LocalDateTime createdAt;
        public ReviewInfo(Review review) {
            this.id = review.getReviewId();
            this.rating = review.getRating();
            this.comment = review.getComment();
            this.createdAt = review.getCreatedAt();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class SearchReviewsResult {
        Page<ReviewInfo> reviewInfos;
        public SearchReviewsResult(Page<Review> reviews) {
            reviewInfos = reviews.map(ReviewInfo::new);
        }
    }

}
