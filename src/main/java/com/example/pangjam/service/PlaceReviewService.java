package com.example.pangjam.service;

import com.example.pangjam.dto.PlaceReviewCreateDto;
import com.example.pangjam.dto.PlaceReviewInfoDto;
import com.example.pangjam.entity.Place;
import com.example.pangjam.entity.PlaceReview;
import com.example.pangjam.repository.PlaceRepository;
import com.example.pangjam.repository.PlaceReviewRepository;
import com.example.pangjam.util.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceReviewService {

    @Autowired
    private PlaceReviewRepository placeReviewRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private Mapper<PlaceReviewInfoDto, PlaceReview> placeReviewMapper;

    @Transactional
    public PlaceReviewInfoDto createPlaceReview(Long placeId, PlaceReviewCreateDto createDto) {
        PlaceReview placeReview = new PlaceReview();
        Place place = placeRepository.findById(placeId).orElseThrow(() -> new IllegalArgumentException("Invalid place id"));
        placeReview.setPlace(place);
        placeReview.setRating(createDto.getRating());
        placeReview.setComment(createDto.getComment());
        placeReview.setCreatedAt(LocalDateTime.now());
        PlaceReview savedReview = placeReviewRepository.save(placeReview);

        place.setReviewCount(place.getReviewCount() + 1);
        switch (createDto.getRating()) {
            case 1:
                place.setRating1Total(place.getRating1Total() + 1);
                break;
            case 2:
                place.setRating2Total(place.getRating2Total() + 1);
                break;
            case 3:
                place.setRating3Total(place.getRating3Total() + 1);
                break;
            case 4:
                place.setRating4Total(place.getRating4Total() + 1);
                break;
            case 5:
                place.setRating5Total(place.getRating5Total() + 1);
                break;
        }

        placeRepository.save(place);

        return placeReviewMapper.convertToDto(savedReview);
    }

    public Page<PlaceReviewInfoDto> getPlaceReviews(int page, Long placeId, String sortBy) {
        Pageable pageable;
        if ("별점순".equals(sortBy)) {
            pageable = PageRequest.of(page - 1, 15, Sort.by("rating").descending());
        } else {
            pageable = PageRequest.of(page - 1, 15, Sort.by("createdAt").descending());
        }
    
        Page<PlaceReview> placeReviewsPage = placeReviewRepository.findByPlace_PlacesId(placeId, pageable);
    
        List<PlaceReviewInfoDto> placeReviewInfoDtos = placeReviewsPage.getContent().stream()
                .map(placeReviewMapper::convertToDto)
                .collect(Collectors.toList());
    
        return new PageImpl<>(placeReviewInfoDtos, pageable, placeReviewsPage.getTotalElements());
    }
}