package com.example.pangjam.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceReviewInfoDto {
    private Long reviewId;
    private Long placeId;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;
}