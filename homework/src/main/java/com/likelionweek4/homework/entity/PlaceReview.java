package com.likelionweek4.homework.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class PlaceReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewId;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    private int rating;
    private String comment;
    private LocalDateTime createdAt;

    protected PlaceReview() {}

    public PlaceReview(Place place, int rating, String comment, LocalDateTime createdAt) {
        this.place = place;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public void updateInfo(int rating, String comment, LocalDateTime createdAt) {
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }
}
