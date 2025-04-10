package com.likelionweek4.homework.dto.restaurant;

import com.likelionweek4.homework.dto.review.ReviewResponseDTO;
import com.likelionweek4.homework.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class RestaurantResponseDTO {

    @Getter
    @NoArgsConstructor
    public static class RestaurantInfo {
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
        public RestaurantInfo(Restaurant restaurant) {
            this.id = restaurant.getRestaurantId();
            this.name = restaurant.getName();
            this.address = restaurant.getAddress();
            this.phone = restaurant.getPhone();
            this.distance = restaurant.getDistance();
            this.latitude = restaurant.getLatitude();
            this.longitude = restaurant.getLongitude();
            this.categoryGroup = restaurant.getCategoryGroup();
            this.category = restaurant.getCategory();
            this.rating = restaurant.getRating();
            restaurant.getReviews().forEach(review -> {
                this.reviews.add(new ReviewResponseDTO.ReviewInfo(review));
            });
        }
    }

    @Getter
    @NoArgsConstructor
    public static class SearchRestaurantResult {
        List<RestaurantInfo> restaurantInfos = new ArrayList<>();
        public SearchRestaurantResult(List<Restaurant> restaurants) {
            restaurants.forEach(restaurant -> {
                restaurantInfos.add(new RestaurantInfo(restaurant));
            });
        }
    }
}
