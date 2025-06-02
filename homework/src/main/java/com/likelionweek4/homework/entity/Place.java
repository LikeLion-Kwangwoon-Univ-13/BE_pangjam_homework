package com.likelionweek4.homework.entity;


import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;
    private String name;
    private String address;
    private String phone;
    private int distance;
    private double latitude;
    private double longitude;
    private String category;
    private double averageRating;
    private int reviewCount;

    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlaceReview> placeReviews = new ArrayList<>();

    @OneToOne(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private PlaceRating placeRating;

    protected Place() {}

    public Place(String name, String address, String phone, int distance, double latitude, double longitude, String category, String imageUrl) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.distance = distance;
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
        this.imageUrl = imageUrl;
        this.averageRating = 0.0;
        this.reviewCount = 0;
    }

    public void updateAverageRating(double averageRating) {
        if(averageRating > 0.0) {
            this.averageRating = Math.round(averageRating*100.0)/100.0;
            return;
        }
        this.averageRating = 0.0;
        this.reviewCount++;
    }
}
