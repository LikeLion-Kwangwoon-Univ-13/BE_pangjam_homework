package com.likelionweek4.homework.dto.placereview;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class PlaceReviewRequestDTO {


    @Getter
    @Setter
    @AllArgsConstructor
    public static class CreateReviewInfo {
        private Long placeId;
        private int rating;
        private String comment;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class SearchReviewsInfo {
        private Long placeId;
        private String sortBy = "latest";
        private int page;
    }
}
