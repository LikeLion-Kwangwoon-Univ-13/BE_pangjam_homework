package com.likelionweek4.homework.dto.review;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ReviewRequestDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CreateReviewDTO {
        private int rating;
        private String comment;
    }
}
