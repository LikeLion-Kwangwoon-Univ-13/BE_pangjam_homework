package com.likelionweek4.homework.dto.review;

import com.likelionweek4.homework.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class ReviewRequestDTO {


    @Getter
    @AllArgsConstructor
    public static class CreateReviewInfo {
        private Long placeId;
        private int rating;
        private String comment;
        private LocalDateTime createdAt;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class SearchReviewsInfo {
        private Long placeId;
        private String sortBy = "latest";
        private int page;
        private int size;
    }

    @Getter
    @AllArgsConstructor
    public static class UpdateReviewInfo {
        @Setter
        private Long reviewId;
        private int rating;
        private String comment;
        private LocalDateTime createdAt;
    }

    @Getter
    @AllArgsConstructor
    public static class DeleteReviewInfo {
        private Long reviewId;
    }
}
