package com.likelionweek4.homework.service;

import com.likelionweek4.homework.dto.review.ReviewRequestDTO;
import com.likelionweek4.homework.entity.Place;
import com.likelionweek4.homework.entity.Review;
import com.likelionweek4.homework.repository.PlaceRepository;
import com.likelionweek4.homework.repository.ReviewRepository;
import com.likelionweek4.homework.validator.review.CreateReviewInfoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final PlaceRepository placeRepository;
    private final ReviewRepository reviewRepository;

    public Review create(ReviewRequestDTO.CreateReviewInfo requestDTO) {
        CreateReviewInfoValidator.validate(requestDTO);
        Place place = getPlace(requestDTO.getPlaceId());
        Review review = new Review(
                place,
                requestDTO.getRating(),
                requestDTO.getComment(),
                requestDTO.getCreatedAt()
        );
        reviewRepository.save(review);
        place.updateRating(reviewRepository.findAverageRatingByPlaceId(place.getPlaceId()));
        placeRepository.save(place);
        return review;
    }

    public List<Review> searchReviewByPlaceId(ReviewRequestDTO.SearchReviewsInfo requestDTO) {
        Long placeId = requestDTO.getPlaceId();
        String sortBy = requestDTO.getSortBy();
        return getReviewList(sortBy, placeId);
    }

    private List<Review> getReviewList(String sortBy, Long placeId) {
        if(sortBy.equals("latest")) {
            return reviewRepository.findByPlace_placeId(placeId, Sort.by(Sort.Direction.DESC, "createdAt"));
        }
        else if(sortBy.equals("oldest")) {
            return reviewRepository.findByPlace_placeId(placeId, Sort.by(Sort.Direction.ASC, "createdAt"));
        }
        else if(sortBy.equals("lowRating")) {
            return reviewRepository.findByPlace_placeId(placeId, Sort.by(Sort.Direction.ASC, "rating"));
        }
        else if(sortBy.equals("highRating")) {
            return reviewRepository.findByPlace_placeId(placeId, Sort.by(Sort.Direction.DESC, "rating"));
        }
        throw new IllegalArgumentException("리뷰 조회 정렬 기준이 없습니다. (latest, oldest, lowRating, highRating)");
    }

    public Review update(ReviewRequestDTO.UpdateReviewInfo requestDTO) {
        Review review = getReview(requestDTO.getReviewId());
        review.updateInfo(
                requestDTO.getRating(),
                requestDTO.getComment(),
                requestDTO.getCreatedAt()
        );
        reviewRepository.save(review);
        Place place = review.getPlace();
        place.updateRating(reviewRepository.findAverageRatingByPlaceId(place.getPlaceId()));
        placeRepository.save(place);
        return review;
    }

    public void deleteReview(ReviewRequestDTO.DeleteReviewInfo requestDTO) {
        Review review = getReview(requestDTO.getReviewId());
        reviewRepository.delete(review);
        Place place = review.getPlace();
        Double averageRating = getAverageRatingByPlaceId(place);
        place.updateRating(averageRating);
        placeRepository.save(place);
    }

    private Double getAverageRatingByPlaceId(Place place) {
        Double averageRating = reviewRepository.findAverageRatingByPlaceId(place.getPlaceId());
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

    private Place getPlace(Long placeId) {
        Place place = placeRepository.findById(placeId).orElse(null);
        if(place == null) {
            throw new IllegalArgumentException("해당 식당이 존재하지 않습니다.");
        }
        return place;
    }
}
