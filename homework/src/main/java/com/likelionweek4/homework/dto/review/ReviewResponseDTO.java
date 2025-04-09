package com.likelionweek4.homework.dto.review;

import com.likelionweek4.homework.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReviewResponseDTO {


    @Getter
    @NoArgsConstructor
    public static class ReviewInfo {
        private Long id;
        private int rating;
        private String comment;
        private LocalDateTime created_at;
        public ReviewInfo(Review review) {
            this.id = review.getReviewId();
            this.rating = review.getRating();
            this.comment = review.getComment();
            this.created_at = review.getCreated_at();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class SearchReviewsResult {
        List<ReviewInfo> reviewInfos = new ArrayList<>();
        public SearchReviewsResult(List<Review> reviews) {
            reviews.forEach(review -> reviewInfos.add(new ReviewInfo(review)));
        }
    }

}
