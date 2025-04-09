package com.likelionweek4.homework.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewId;

    @ManyToOne
    @JoinColumn(name = "restaurantId")
    private Restaurant restaurant;

    private int rating;
    private String comment;
    private LocalDateTime created_at;

    protected Review() {}

    public Review(Restaurant restaurant, int rating, String comment, LocalDateTime created_at) {
        this.restaurant = restaurant;
        this.rating = rating;
        this.comment = comment;
        this.created_at = created_at;
    }

    public void updateInfo(int rating, String comment, LocalDateTime created_at) {
        this.rating = rating;
        this.comment = comment;
        this.created_at = created_at;
    }
}
