package com.example.pangjam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCreateDto {
    private Integer rating;
    private String comment;

    public void setRating(Integer rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        this.rating = rating;
    }

    public void setComment(String comment) {
        if (comment.length() == 0) {
            throw new IllegalArgumentException("Comment must be exist");
        }
        this.comment = comment;
    }
}