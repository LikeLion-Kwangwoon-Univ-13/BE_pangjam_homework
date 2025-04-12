package com.likelionweek4.homework.validator.place;

import com.likelionweek4.homework.dto.place.PlaceRequestDTO;

public class UpdatePlaceInfoValidator {
    public static void validate(PlaceRequestDTO.UpdatePlaceInfo requestDTO) {
        validateName(requestDTO);
        validateLatitude(requestDTO);
        validateLongitude(requestDTO);
        validateCategory(requestDTO);
    }

    private static void validateCategory(PlaceRequestDTO.UpdatePlaceInfo requestDTO) {
        if (requestDTO.getCategory() == null || requestDTO.getCategory().trim().isEmpty() || requestDTO.getCategory().length() < 2 || requestDTO.getCategory().length() > 30) {
            throw new IllegalArgumentException("카테고리는 2자 이상 30자 이하의 문자열이어야 합니다.");
        }
    }

    private static void validateLongitude(PlaceRequestDTO.UpdatePlaceInfo requestDTO) {
        if (requestDTO.getLongitude() < -180 || requestDTO.getLongitude() > 180) {
            throw new IllegalArgumentException("경도는 -180도에서 180도 사이여야 합니다.");
        }
    }

    private static void validateLatitude(PlaceRequestDTO.UpdatePlaceInfo requestDTO) {
        if (requestDTO.getLatitude() < -90 || requestDTO.getLatitude() > 90) {
            throw new IllegalArgumentException("위도는 -90도에서 90도 사이여야 합니다.");
        }
    }

    private static void validateName(PlaceRequestDTO.UpdatePlaceInfo requestDTO) {
        if (requestDTO.getName() == null || requestDTO.getName().trim().isEmpty() || requestDTO.getName().length() < 2 || requestDTO.getName().length() > 50) {
            throw new IllegalArgumentException("식당 이름 공백일 수 없습니다.");
        }
    }
}
