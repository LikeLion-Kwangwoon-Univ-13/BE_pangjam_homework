package com.example.pangjam.util.mapper;

import org.springframework.stereotype.Component;

import com.example.pangjam.dto.PlaceReviewInfoDto;
import com.example.pangjam.entity.PlaceReview;
import com.example.pangjam.util.Mapper;

@Component
public class PlaceReviewMapper implements Mapper<PlaceReviewInfoDto, PlaceReview> {
    @Override
    public PlaceReviewInfoDto convertToDto(PlaceReview entity) {
        PlaceReviewInfoDto dto = PlaceReviewInfoDto.builder()
            .reviewId(entity.getReviewId())
            .placeId(entity.getPlace().getPlacesId())
            .rating(entity.getRating())
            .comment(entity.getComment())
            .createdAt(entity.getCreatedAt())
            .build();
        return dto;
    }
}
