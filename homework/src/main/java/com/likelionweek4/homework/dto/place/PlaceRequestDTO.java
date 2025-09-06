package com.likelionweek4.homework.dto.place;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class PlaceRequestDTO {

    @Getter
    @Setter
    public static class SearchPlaceConditionInfo {
        private String name;
        private String category;
        private int page;
        private int size = 15;
    }

    @Getter
    @AllArgsConstructor
    public static class PlaceIdDTO {
        Long id;
    }
}
