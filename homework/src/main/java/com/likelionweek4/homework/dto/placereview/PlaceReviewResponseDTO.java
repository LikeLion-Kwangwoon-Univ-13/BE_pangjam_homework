package com.likelionweek4.homework.dto.placereview;

import com.likelionweek4.homework.entity.PlaceReview;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import java.time.LocalDateTime;

public class PlaceReviewResponseDTO {


    @Getter
    @NoArgsConstructor
    public static class ReviewInfo {
        private Long id;
        private int rating;
        private String comment;
        private LocalDateTime createdAt;
        public ReviewInfo(PlaceReview placeReview) {
            this.id = placeReview.getReviewId();
            this.rating = placeReview.getRating();
            this.comment = placeReview.getComment();
            this.createdAt = placeReview.getCreatedAt();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class SearchReviewsResult {
        Page<ReviewInfo> reviewInfos;
        public SearchReviewsResult(Page<PlaceReview> reviews) {
            reviewInfos = reviews.map(ReviewInfo::new);
        }
    }

}
