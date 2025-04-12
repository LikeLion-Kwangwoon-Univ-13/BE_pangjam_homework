package com.likelionweek4.homework.validator.place;

import com.likelionweek4.homework.dto.place.PlaceRequestDTO;

public class SearchPlaceConditionValidator {
    public static void validate(PlaceRequestDTO.SearchPlaceConditionInfo requestDTO) {
        validateSearchCondition(requestDTO);
    }

    private static void validateSearchCondition(PlaceRequestDTO.SearchPlaceConditionInfo requestDTO) {
        if(requestDTO.getIsRatingASC() == null && requestDTO.getName() == null && requestDTO.getIsDistanceASC() == null && requestDTO.getCategory() == null && requestDTO.getCategoryGroup() == null) {
            throw new IllegalArgumentException("검색 조건이 존재하지 않습니다.");
        }
    }
}
