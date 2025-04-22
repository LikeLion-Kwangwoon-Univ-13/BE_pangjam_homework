package com.likelionweek4.homework.entity;


import jakarta.annotation.Nullable;
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
//    @Nullable
//    @Column(length = 1024)
    @Column(columnDefinition = "TEXT")
    private String imageUrl;
    private double rating = 0.0;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

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
    }

    public void updateInfo(String name, String address, int distance, double latitude, double longitude, String category, String imageUrl) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.distance = distance;
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public void updateRating(double averageRating) {
        if(averageRating > 0.0) {
//            double ratingResult = Math.round(averageRating * 100.0) / 100.0;
//            String averageRatingString = String.format("%.2f", ratingResult);
            this.rating = averageRating;
            return;
        }
        this.rating = 0.0;
    }
}
