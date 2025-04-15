package com.likelionweek4.homework.dto.place;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class PlaceRequestDTO {

    @Getter
    @AllArgsConstructor
    public static class CreatePlaceInfo {
        String name;
        String address;
        String phone;
        int distance;
        double latitude;
        double longitude;
        String categoryGroup;
        String category;
    }

    @Getter
    @Setter
    public static class SearchPlaceConditionInfo {
        private String name;
        private String categoryGroup;
        private String category;
        private Boolean isDistanceASC; // True: 오름차순, False: 내림차순
        private Boolean isRatingASC; // True: 오름차순, False: 내림차순 (기본값 : 평점 높은순)
        private int page;
        private int size;
    }


    @Getter
    @AllArgsConstructor
    public static class UpdatePlaceInfo {
        @Setter
        Long id;
        String name;
        String address;
        String phone;
        int distance;
        double latitude;
        double longitude;
        String categoryGroup;
        String category;
    }

    @Getter
    @AllArgsConstructor
    public static class PlaceIdDTO {
        Long id;
    }
}
