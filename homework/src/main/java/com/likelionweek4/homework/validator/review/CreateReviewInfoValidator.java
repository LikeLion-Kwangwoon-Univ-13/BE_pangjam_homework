package com.likelionweek4.homework.validator.review;

import com.likelionweek4.homework.dto.review.ReviewRequestDTO;
import com.likelionweek4.homework.entity.Review;

import java.time.LocalDateTime;

public class CreateReviewInfoValidator {
    public static void validate (ReviewRequestDTO.CreateReviewInfo requestDTO) {
        validateRating(requestDTO.getRating());
    }

    private static void validateRating(int rating) {
        if(rating < 0 || rating > 5) {
            throw new IllegalArgumentException("별점은 최소 0개 최대 5개입니다.");
        }
    }
}
