package com.example.pangjam.service;

import com.example.pangjam.dto.ReviewCreateDto;
import com.example.pangjam.dto.ReviewInfoDto;
import com.example.pangjam.entity.Review;
import com.example.pangjam.repository.ReviewRepository;
import com.example.pangjam.util.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private Mapper<ReviewInfoDto, Review> reviewMapper;

    public ReviewInfoDto createReview(ReviewCreateDto createDto) {
        Review review = new Review();
        review.setRating(createDto.getRating());
        review.setComment(createDto.getComment());
        review.setCreatedAt(LocalDateTime.now());
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.convertToDto(savedReview);
    }

    public Page<ReviewInfoDto> getReviews(int page) {
        Pageable pageable = PageRequest.of(page - 1, 15);
        Page<Review> reviewsPage = reviewRepository.findAll(pageable);
    
        List<ReviewInfoDto> reviewInfoDtos = reviewsPage.getContent().stream()
                .map(reviewMapper::convertToDto)
                .collect(Collectors.toList());
    
        return new PageImpl<>(reviewInfoDtos, pageable, reviewsPage.getTotalElements());
    }
}