package com.example.pangjam.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "places")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "places_id")
    private Long placesId;

    @Column(name = "category")
    private String category;

    @Column(name = "name")
    private String name;

    @Column(name = "rating_1_total")
    private Integer rating1Total;

    @Column(name = "rating_2_total")
    private Integer rating2Total;

    @Column(name = "rating_3_total")
    private Integer rating3Total;

    @Column(name = "rating_4_total")
    private Integer rating4Total;

    @Column(name = "rating_5_total")
    private Integer rating5Total;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "distance")
    private Integer distance;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "review_count")
    private Integer reviewCount;

    public Place() {
        this.reviewCount = 0;
    }
}