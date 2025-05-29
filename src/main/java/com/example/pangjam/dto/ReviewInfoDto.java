package com.example.pangjam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewInfoDto {
    private Long reviewId;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;
}