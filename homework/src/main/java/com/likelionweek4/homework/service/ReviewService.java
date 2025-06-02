package com.likelionweek4.homework.service;

import com.likelionweek4.homework.dto.review.ReviewRequestDTO;
import com.likelionweek4.homework.dto.review.ReviewResponseDTO;
import com.likelionweek4.homework.entity.Review;
import com.likelionweek4.homework.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewResponseDTO.CreateReviewDTO createReview(ReviewRequestDTO.CreateReviewDTO requestDTO) {
        Review review = new Review(requestDTO.getRating(), requestDTO.getComment());
        reviewRepository.save(review);
        log.error("dto rating is {}", requestDTO.getRating());
        log.error("dto comment is {}", requestDTO.getComment());
        log.error("Review rating is {}", review.getRating());
        log.error("Review comment is {}", review.getComment());
        return new ReviewResponseDTO.CreateReviewDTO(review);
    }

    public ReviewResponseDTO.SearchReviewDTO searchReview(ReviewRequestDTO.SearchReviewDTO requestDTO) {
        Pageable pageable = PageRequest.of(requestDTO.getPage()-1, requestDTO.getSize(), Sort.by(Sort.Direction.DESC,"createdAt"));
        return new ReviewResponseDTO.SearchReviewDTO(reviewRepository.findAll(pageable));
    }
}
