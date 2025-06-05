package com.likelionweek4.homework.dto.place;

import com.likelionweek4.homework.dto.placereview.PlaceReviewResponseDTO;
import com.likelionweek4.homework.entity.Place;
import com.likelionweek4.homework.entity.PlaceRating;
import com.likelionweek4.homework.entity.PlaceReview;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        private double averageRating;
        private int[] ratingsCount = {0,0,0,0,0};
        private int reviewCount;
        private String imageUrl;
        List<PlaceReviewResponseDTO.ReviewInfo> reviews = new ArrayList<>();
        public PlaceInfo(Place place) {
            this.id = place.getPlaceId();
            this.name = place.getName();
            this.address = place.getAddress();
            this.phone = place.getPhone();
            this.distance = place.getDistance();
            this.latitude = place.getLatitude();
            this.longitude = place.getLongitude();
            this.category = place.getCategory();
            this.averageRating = place.getAverageRating();
            this.reviewCount = place.getReviewCount();

            PlaceRating placeRating = place.getPlaceRating();
            if(placeRating != null) {
                this.ratingsCount[0] = placeRating.getOne();
                this.ratingsCount[1] = placeRating.getTwo();
                this.ratingsCount[2] = placeRating.getThree();
                this.ratingsCount[3] = placeRating.getFour();
                this.ratingsCount[4] = placeRating.getFive();
            }

            this.imageUrl = place.getImageUrl();

            place.getPlaceReviews().sort(Comparator.comparing(PlaceReview::getCreatedAt).reversed());
            if(place.getPlaceReviews().size() > 3) {
                for (int i = 0; i < 3; i++) {
                    this.reviews.add(new PlaceReviewResponseDTO.ReviewInfo(place.getPlaceReviews().get(i)));
                }
            }
            else {
                for(PlaceReview placeReview : place.getPlaceReviews()) {
                    this.reviews.add(new PlaceReviewResponseDTO.ReviewInfo(placeReview));
                }
            }
        }
    }

    @Getter
    @NoArgsConstructor
    public static class SearchPlaceResult {
        List<PlaceInfo> placeInfosList;
        Boolean last;
        public SearchPlaceResult(Page<Place> places) {
            this.placeInfosList = places.stream()
                            .map(PlaceInfo::new)
                            .collect(Collectors.toList());
            this.last = places.isLast();
        }
    }
}
