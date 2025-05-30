package com.likelionweek4.homework.service;

import com.likelionweek4.homework.dto.review.ReviewRequestDTO;
import com.likelionweek4.homework.dto.review.ReviewResponseDTO;
import com.likelionweek4.homework.entity.Review;
import com.likelionweek4.homework.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewResponseDTO.CreateReviewDTO createReview(ReviewRequestDTO.CreateReviewDTO requestDTO) {
        Review review = new Review(requestDTO.getRating(), requestDTO.getComment());
        reviewRepository.save(review);
        return new ReviewResponseDTO.CreateReviewDTO(review.getId(), review.getRating(), review.getComment(), review.getCreatedAt());
    }

    public ReviewResponseDTO.SearchReviewDTO searchReview(ReviewRequestDTO.SearchReviewDTO requestDTO) {
        Pageable pageable = PageRequest.of(requestDTO.getPage()-1, requestDTO.getSize(), Sort.by(Sort.Direction.DESC,"createdAt"));
        return new ReviewResponseDTO.SearchReviewDTO(reviewRepository.findAll(pageable));
    }
}
