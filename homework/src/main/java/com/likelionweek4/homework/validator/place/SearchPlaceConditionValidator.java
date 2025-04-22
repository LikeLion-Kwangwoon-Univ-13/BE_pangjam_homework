package com.likelionweek4.homework.validator.place;

import com.likelionweek4.homework.dto.place.PlaceRequestDTO;

public class SearchPlaceConditionValidator {
    public static void validate(PlaceRequestDTO.SearchPlaceConditionInfo requestDTO) {
        validateSortParameters(requestDTO);
    }

    private static void validateSortParameters(PlaceRequestDTO.SearchPlaceConditionInfo requestDTO) {
        if(requestDTO.getIsDistanceASC() != null && requestDTO.getIsRatingASC() != null) {
            throw new IllegalArgumentException("정렬 기준은 하나만 선택해야 합니다. (아무것도 선택하지 않았다면 기본값은 평점 높은 순입니다.");
        }
    }
}
