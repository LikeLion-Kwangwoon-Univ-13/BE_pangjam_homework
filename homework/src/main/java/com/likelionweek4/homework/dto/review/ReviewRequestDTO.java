package com.likelionweek4.homework.dto.review;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class ReviewRequestDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CreateReviewDTO {
        private int rating;
        private String comment;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    public static class SearchReviewDTO {
        private int page;
        private int size = 2;
    }
}
