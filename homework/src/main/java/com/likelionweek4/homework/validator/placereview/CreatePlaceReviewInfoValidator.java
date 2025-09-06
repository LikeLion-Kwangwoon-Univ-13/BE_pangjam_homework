package com.likelionweek4.homework.validator.placereview;

import com.likelionweek4.homework.dto.placereview.PlaceReviewRequestDTO;

public class CreatePlaceReviewInfoValidator {
    public static void validate (PlaceReviewRequestDTO.CreateReviewInfo requestDTO) {
        validateRating(requestDTO.getRating());
    }

    private static void validateRating(int rating) {
        if(rating < 0 || rating > 5) {
            throw new IllegalArgumentException("별점은 최소 0개 최대 5개입니다.");
        }
    }
}
