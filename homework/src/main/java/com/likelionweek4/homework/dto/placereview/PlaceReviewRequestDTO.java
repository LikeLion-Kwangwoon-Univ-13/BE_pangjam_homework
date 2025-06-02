package com.likelionweek4.homework.dto.placereview;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    @NoArgsConstructor
    public static class SearchReviewsInfo {
        private Long placeId;
        private int sortBy = 0;
        private int page;
        private int size = 15;
    }
}
