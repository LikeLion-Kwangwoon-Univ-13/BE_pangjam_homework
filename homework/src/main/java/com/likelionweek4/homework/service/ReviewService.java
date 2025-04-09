package com.likelionweek4.homework.service;

import com.likelionweek4.homework.dto.review.ReviewRequestDTO;
import com.likelionweek4.homework.entity.Restaurant;
import com.likelionweek4.homework.entity.Review;
import com.likelionweek4.homework.repository.RestaurantRepository;
import com.likelionweek4.homework.repository.ReviewRepository;
import com.likelionweek4.homework.validator.review.CreateReviewInfoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;

    public Review create(ReviewRequestDTO.CreateReviewInfo requestDTO) {
        CreateReviewInfoValidator.validate(requestDTO);
        Restaurant restaurant = getRestaurant(requestDTO.getRestaurantId());
        Review review = new Review(
                restaurant,
                requestDTO.getRating(),
                requestDTO.getComment(),
                requestDTO.getCreated_at()
        );
        reviewRepository.save(review);
        restaurant.updateRating(reviewRepository.findAverageRatingByRestaurantId(restaurant.getRestaurantId()));
        restaurantRepository.save(restaurant);
        return review;
    }

    public List<Review> searchReviewByRestaurantId(ReviewRequestDTO.SearchReviewsInfo requestDTO) {
        return reviewRepository.findByRestaurant_RestaurantId(requestDTO.getRestaurantId());
    }

    public Review update(ReviewRequestDTO.UpdateReviewInfo requestDTO) {
        Review review = getReview(requestDTO.getReviewId());
        review.updateInfo(
                requestDTO.getRating(),
                requestDTO.getComment(),
                requestDTO.getCreated_at()
        );
        reviewRepository.save(review);
        Restaurant restaurant = review.getRestaurant();
        restaurant.updateRating(reviewRepository.findAverageRatingByRestaurantId(restaurant.getRestaurantId()));
        restaurantRepository.save(restaurant);
        return review;
    }

    public void deleteReview(ReviewRequestDTO.DeleteReviewInfo requestDTO) {
        Review review = getReview(requestDTO.getReviewId());
        reviewRepository.delete(review);
        Restaurant restaurant = review.getRestaurant();
        restaurant.updateRating(reviewRepository.findAverageRatingByRestaurantId(restaurant.getRestaurantId()));
        restaurantRepository.save(restaurant);
    }

    private Review getReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review == null) {
            throw new IllegalArgumentException("해당 리뷰가 존재하지 않습니다.");
        }
        return review;
    }

    private Restaurant getRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        if(restaurant == null) {
            throw new IllegalArgumentException("해당 식당이 존재하지 않습니다.");
        }
        return restaurant;
    }
}
