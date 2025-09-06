package com.likelionweek4.homework.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int Rating;
    private String comment;
    private LocalDateTime createdAt;

    public Review(int Rating, String comment) {
        this.Rating = Rating;
        this.comment = comment;
        this.createdAt = LocalDateTime.now();
    }
}
