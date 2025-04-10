package com.likelionweek4.homework.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class RestaurantRequestDTO {

    @Getter
    @AllArgsConstructor
    public static class CreateRestaurantInfo {
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
    public static class SearchRestaurantConditionInfo {
        private String name;
        private String categoryGroup;
        private String category;
        private Boolean distance; // True: 오름차순, False: 내림차순
    }


    @Getter
    @AllArgsConstructor
    public static class UpdateRestaurantInfo {
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
    public static class RestaurantIdDTO {
        Long id;
    }
}
