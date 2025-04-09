package com.likelionweek4.homework.service;

import com.likelionweek4.homework.dto.MessageResponseDTO;
import com.likelionweek4.homework.dto.review.ReviewRequestDTO;
import com.likelionweek4.homework.entity.Restaurant;
import com.likelionweek4.homework.entity.Review;
import com.likelionweek4.homework.repository.RestaurantRepository;
import com.likelionweek4.homework.repository.ReviewRepository;
import com.likelionweek4.homework.validator.review.CreateReviewInfoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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
        Long restaurantId = requestDTO.getRestaurantId();
        String sortBy = requestDTO.getSortBy();
        if(sortBy.equals("latest")) {
            return reviewRepository.findByRestaurant_RestaurantId(restaurantId, Sort.by(Sort.Direction.DESC, "created_at"));
        }
        else if(sortBy.equals("oldest")) {
            return reviewRepository.findByRestaurant_RestaurantId(restaurantId, Sort.by(Sort.Direction.ASC, "created_at"));
        }
        else if(sortBy.equals("lowRating")) {
            return reviewRepository.findByRestaurant_RestaurantId(restaurantId, Sort.by(Sort.Direction.ASC, "rating"));
        }
        else if(sortBy.equals("highRating")) {
            return reviewRepository.findByRestaurant_RestaurantId(restaurantId, Sort.by(Sort.Direction.DESC, "rating"));
        }
        throw new IllegalArgumentException("리뷰 조회 정렬 기준이 없습니다. (latest, oldest, lowRating, highRating)");
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
        Double averageRating = getAverageRatingByRestaurantId(restaurant);
        restaurant.updateRating(averageRating);
        restaurantRepository.save(restaurant);
    }

    private Double getAverageRatingByRestaurantId(Restaurant restaurant) {
        Double averageRating = reviewRepository.findAverageRatingByRestaurantId(restaurant.getRestaurantId());
        if(averageRating == null) return 0.0;
        return averageRating;
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
