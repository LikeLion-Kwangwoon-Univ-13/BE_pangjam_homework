package com.likelionweek4.homework.dto.place;

import com.likelionweek4.homework.dto.review.ReviewResponseDTO;
import com.likelionweek4.homework.entity.Place;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class PlaceResponseDTO {

    @Getter
    @NoArgsConstructor
    public static class PlaceInfo {
        private Long id;
        private String name;
        private String address;
        private String phone;
        private int distance;
        private double latitude;
        private double longitude;
        private String categoryGroup;
        private String category;
        private double rating;
        List<ReviewResponseDTO.ReviewInfo> reviews = new ArrayList<>();
        public PlaceInfo(Place place) {
            this.id = place.getPlaceId();
            this.name = place.getName();
            this.address = place.getAddress();
            this.phone = place.getPhone();
            this.distance = place.getDistance();
            this.latitude = place.getLatitude();
            this.longitude = place.getLongitude();
            this.categoryGroup = place.getCategoryGroup();
            this.category = place.getCategory();
            this.rating = place.getRating();
            place.getReviews().forEach(review -> {
                this.reviews.add(new ReviewResponseDTO.ReviewInfo(review));
            });
        }
    }

    @Getter
    @NoArgsConstructor
    public static class SearchPlaceResult {
        List<PlaceInfo> placeInfos = new ArrayList<>();
        public SearchPlaceResult(List<Place> places) {
            places.forEach(place -> {
                placeInfos.add(new PlaceInfo(place));
            });
        }
    }
}
