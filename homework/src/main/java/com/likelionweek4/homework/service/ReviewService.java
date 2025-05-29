package com.likelionweek4.homework.service;

import com.likelionweek4.homework.dto.review.ReviewRequestDTO;
import com.likelionweek4.homework.dto.review.ReviewResponseDTO;
import com.likelionweek4.homework.entity.Review;
import com.likelionweek4.homework.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewResponseDTO.CreateReviewDTO createReview(ReviewRequestDTO.CreateReviewDTO requestDTO) {
        Review review = new Review(requestDTO.getRating(), requestDTO.getComment());
        reviewRepository.save(review);
        return new ReviewResponseDTO.CreateReviewDTO(review.getId(), review.getRating(), review.getComment(), review.getCreatedAt());
    }
}
