package com.likelionweek4.homework.service;

import com.likelionweek4.homework.dto.MessageResponseDTO;
import com.likelionweek4.homework.dto.review.ReviewRequestDTO;
import com.likelionweek4.homework.dto.review.ReviewResponseDTO;
import com.likelionweek4.homework.entity.Place;
import com.likelionweek4.homework.entity.Review;
import com.likelionweek4.homework.repository.place.PlaceRepository;
import com.likelionweek4.homework.repository.review.ReviewRepository;
import com.likelionweek4.homework.validator.review.CreateReviewInfoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final PlaceRepository placeRepository;
    private final ReviewRepository reviewRepository;

    public ReviewResponseDTO.ReviewInfo create(ReviewRequestDTO.CreateReviewInfo requestDTO) {
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
        return new ReviewResponseDTO.ReviewInfo(review);
    }

    public ReviewResponseDTO.SearchReviewsResult searchReviewByPlaceId(ReviewRequestDTO.SearchReviewsInfo requestDTO) {
        return new ReviewResponseDTO.SearchReviewsResult(getReviewList(requestDTO));
    }

    private Page<Review> getReviewList(ReviewRequestDTO.SearchReviewsInfo requestDTO) {
        Long placeId = requestDTO.getPlaceId();
        String sortBy = requestDTO.getSortBy();
        if(sortBy.equals("latest")) {
            Pageable pageable = PageRequest.of(requestDTO.getPage()-1, requestDTO.getSize(), Sort.by(Sort.Direction.DESC,"createdAt"));
            return reviewRepository.findByPlace_placeId(placeId, pageable);
        }
        else if(sortBy.equals("oldest")) {
            Pageable pageable = PageRequest.of(requestDTO.getPage()-1, requestDTO.getSize(), Sort.by(Sort.Direction.ASC,"createdAt"));
            return reviewRepository.findByPlace_placeId(placeId, pageable);
        }
        else if(sortBy.equals("lowRating")) {
            Pageable pageable = PageRequest.of(requestDTO.getPage()-1, requestDTO.getSize(), Sort.by(Sort.Direction.ASC,"rating"));
            return reviewRepository.findByPlace_placeId(placeId, pageable);
        }
        else if(sortBy.equals("highRating")) {
            Pageable pageable = PageRequest.of(requestDTO.getPage()-1, requestDTO.getSize(), Sort.by(Sort.Direction.DESC,"rating"));
            return reviewRepository.findByPlace_placeId(placeId, pageable);
        }
        throw new IllegalArgumentException("리뷰 조회 정렬 기준이 없습니다. (latest, oldest, lowRating, highRating)");
    }

    public ReviewResponseDTO.ReviewInfo update(ReviewRequestDTO.UpdateReviewInfo requestDTO) {
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
        return new ReviewResponseDTO.ReviewInfo(review);
    }

    public MessageResponseDTO.Message deleteReview(ReviewRequestDTO.DeleteReviewInfo requestDTO) {
        Review review = getReview(requestDTO.getReviewId());
        reviewRepository.delete(review);
        Place place = review.getPlace();
        Double averageRating = getAverageRatingByPlaceId(place);
        place.updateRating(averageRating);
        placeRepository.save(place);
        return new MessageResponseDTO.Message("삭제가 완료되었습니다.");
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
