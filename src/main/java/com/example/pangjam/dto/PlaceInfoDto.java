package com.example.pangjam.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaceInfoDto {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private Integer distance;
    private Double longitude;
    private Double latitude;
    private String category;
    private Double averageRating;
    private int[] ratingsCount;
    private Integer reviewCount;
    private String imageURL;
    private List<PlaceReviewInfoDto> reviews;
}
/*
 {
  "id": 13,
  "name": "본도시락 광운대역점",
  "address": "서울 노원구 석계로18길 23",
  "phone": "02-943-4282",
  "distance": 517,
  "latitude": 37.624032488252,
  "longitude": 127.060707544382,
  "category": "도시락",
  "averageRating": 0.0,
  "ratingsCount": [0,0,0,0,1]
  "reviewCount": 1,
  "imageURL" : "notion.so/b86d&pm=s",
  "reviews": [
    {
      "id": 1,
      "rating": 5,
      "comment": "깔끔하고 맛있어요",
      "createdAt": "2025-05-01T14:20:00"
    }
  ]
}
 */