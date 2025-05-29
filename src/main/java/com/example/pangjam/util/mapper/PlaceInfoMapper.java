package com.example.pangjam.util.mapper;

import org.springframework.stereotype.Component;

import com.example.pangjam.dto.PlaceInfoDto;
import com.example.pangjam.entity.Place;
import com.example.pangjam.util.Mapper;

@Component
public class PlaceInfoMapper implements Mapper<PlaceInfoDto, Place> {
    @Override
    public PlaceInfoDto convertToDto(Place entity) {

        PlaceInfoDto dto = PlaceInfoDto.builder()
                .id(entity.getPlacesId())
                .category(entity.getCategory())
                .name(entity.getName())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .distance(entity.getDistance())
                .longitude(entity.getLongitude())
                .latitude(entity.getLatitude())
                .averageRating(calculateAverageRating(entity))
                .build();

        return dto;
    }
    
    private double calculateAverageRating(Place entity) {
        int totalRating = 0;
        int totalReviews = entity.getReviewCount();

        if (totalReviews != 0) {
            totalRating = (entity.getRating1Total() * 1
                    + entity.getRating2Total() * 2
                    + entity.getRating3Total() * 3
                    + entity.getRating4Total() * 4
                    + entity.getRating5Total() * 5);
        }

        double averageRating = totalReviews > 0 ? (double) totalRating / totalReviews : 0;
        averageRating = Math.round(averageRating * 100.0) / 100.0;

        return averageRating;
    }
}
