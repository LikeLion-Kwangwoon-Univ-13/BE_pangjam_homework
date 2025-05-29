package com.example.pangjam.util.mapper;

import org.springframework.stereotype.Component;

import com.example.pangjam.dto.ReviewInfoDto;
import com.example.pangjam.entity.Review;
import com.example.pangjam.util.Mapper;

@Component
public class ReviewMapper implements Mapper<ReviewInfoDto, Review> {
    @Override
    public ReviewInfoDto convertToDto(Review entity) {
        ReviewInfoDto dto = new ReviewInfoDto();
        dto.setReviewId(entity.getReviewId());
        dto.setRating(entity.getRating());
        dto.setComment(entity.getComment());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
}
