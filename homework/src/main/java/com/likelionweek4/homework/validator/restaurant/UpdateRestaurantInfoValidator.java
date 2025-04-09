package com.likelionweek4.homework.validator.restaurant;

import com.likelionweek4.homework.dto.restaurant.RestaurantRequestDTO;

public class UpdateRestaurantInfoValidator {
    public static void validate(RestaurantRequestDTO.UpdateRestaurantInfo requestDTO) {
        validateName(requestDTO);
        validateAddress(requestDTO);
        validateLatitude(requestDTO);
        validateLongitude(requestDTO);
        validateCategory(requestDTO);
    }

    private static void validateCategory(RestaurantRequestDTO.UpdateRestaurantInfo requestDTO) {
        if (requestDTO.getCategory() == null || requestDTO.getCategory().trim().isEmpty() || requestDTO.getCategory().length() < 2 || requestDTO.getCategory().length() > 30) {
            throw new IllegalArgumentException("카테고리는 2자 이상 30자 이하의 문자열이어야 합니다.");
        }
    }

    private static void validateLongitude(RestaurantRequestDTO.UpdateRestaurantInfo requestDTO) {
        if (requestDTO.getLongitude() < -180 || requestDTO.getLongitude() > 180) {
            throw new IllegalArgumentException("경도는 -180도에서 180도 사이여야 합니다.");
        }
    }

    private static void validateLatitude(RestaurantRequestDTO.UpdateRestaurantInfo requestDTO) {
        if (requestDTO.getLatitude() < -90 || requestDTO.getLatitude() > 90) {
            throw new IllegalArgumentException("위도는 -90도에서 90도 사이여야 합니다.");
        }
    }

    private static void validateAddress(RestaurantRequestDTO.UpdateRestaurantInfo requestDTO) {
        if (requestDTO.getAddress() == null || requestDTO.getAddress().trim().isEmpty() || requestDTO.getAddress().length() < 5 || requestDTO.getAddress().length() > 100) {
            throw new IllegalArgumentException("주소는 5자 이상 100자 이하의 문자열이어야 합니다.");
        }
    }

    private static void validateName(RestaurantRequestDTO.UpdateRestaurantInfo requestDTO) {
        if (requestDTO.getName() == null || requestDTO.getName().trim().isEmpty() || requestDTO.getName().length() < 2 || requestDTO.getName().length() > 50) {
            throw new IllegalArgumentException("식당 이름은 2자 이상 50자 이하의 문자열이어야 합니다.");
        }
    }
}
