package com.likelionweek4.homework.dto.place;

import com.likelionweek4.homework.dto.review.ReviewResponseDTO;
import com.likelionweek4.homework.entity.Place;
import com.likelionweek4.homework.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Comparator;
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
        private String category;
        private double rating;
        private String imageUrl;
        List<ReviewResponseDTO.ReviewInfo> reviews = new ArrayList<>();
        public PlaceInfo(Place place) {
            this.id = place.getPlaceId();
            this.name = place.getName();
            this.address = place.getAddress();
            this.phone = place.getPhone();
            this.distance = place.getDistance();
            this.latitude = place.getLatitude();
            this.longitude = place.getLongitude();
            this.category = place.getCategory();
            this.rating = place.getRating();
            this.imageUrl = place.getImageUrl();

            place.getReviews().sort(Comparator.comparing(Review::getCreatedAt).reversed());
            if(place.getReviews().size() > 3) {
                for (int i = 0; i < 3; i++) {
                    this.reviews.add(new ReviewResponseDTO.ReviewInfo(place.getReviews().get(i)));
                }
            }
            else {
                for(Review review : place.getReviews()) {
                    this.reviews.add(new ReviewResponseDTO.ReviewInfo(review));
                }
            }
        }
    }

    @Getter
    @NoArgsConstructor
    public static class SearchPlaceResult {
        List<PlaceInfo> placeInfosList = new ArrayList<>();
        Page<PlaceInfo> placeInfosPaging;
        public SearchPlaceResult(Page<Place> places, Pageable pageable) {
            places.forEach(place -> {
                placeInfosList.add(new PlaceInfo(place));
            });
            this.placeInfosPaging = new PageImpl<>(placeInfosList, pageable, places.getTotalElements());
        }
    }
}
