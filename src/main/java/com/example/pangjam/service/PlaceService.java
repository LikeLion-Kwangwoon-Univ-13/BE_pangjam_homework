package com.example.pangjam.service;

import com.example.pangjam.dto.PlaceInfoDto;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private PlaceReviewRepository placeReviewRepository;

    @Autowired
    private Mapper<PlaceInfoDto, Place> placeInfoMapper;

    @Autowired
    private Mapper<PlaceReviewInfoDto, PlaceReview> placeReviewMapper;

    public Page<PlaceInfoDto> getPlaces(int page, String category, String name) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<Place> placesPage;

        if (category != null && name != null) {
            placesPage = placeRepository.findByCategoryAndNameContaining(category, name, pageable);
        } else if (name != null) {
            placesPage = placeRepository.findByNameContaining(name, pageable);
        } else if (category != null) {
            placesPage = placeRepository.findByCategory(category, pageable);
        } else {
            placesPage = placeRepository.findAll(pageable);
        }

        List<PlaceInfoDto> placeInfoDtos = placesPage.getContent().stream()
                .map(place -> {
                    PlaceInfoDto dto = placeInfoMapper.convertToDto(place);
                    return dto;
                })
                .collect(Collectors.toList());

        return new PageImpl<>(placeInfoDtos, pageable, placesPage.getTotalElements());
    }

    public PlaceInfoDto getPlace(Long placeId) {
        return placeRepository.findById(placeId)
                .map(place -> {
                    PlaceInfoDto dto = placeInfoMapper.convertToDto(place);
                    int reviewCount = place.getReviewCount();
                    int[] ratingsCount = new int[5];
                    ratingsCount[0] = place.getRating1Total();
                    ratingsCount[1] = place.getRating2Total();
                    ratingsCount[2] = place.getRating3Total();
                    ratingsCount[3] = place.getRating4Total();
                    ratingsCount[4] = place.getRating5Total();

                    PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "createdAt"));
                    List<PlaceReview> placeReviews = placeReviewRepository.findByPlace_PlacesId(placeId, pageRequest).getContent();
                    List<PlaceReviewInfoDto> reviewInfoDtos = placeReviews.stream()
                            .map(placeReviewMapper::convertToDto)
                            .collect(Collectors.toList());
                    
                    dto.setReviews(reviewInfoDtos);
                    dto.setReviewCount(reviewCount);
                    dto.setRatingsCount(ratingsCount);
                    dto.setImageURL(place.getThumbnail());

                    return dto;
                })
                .orElseThrow(() -> new IllegalArgumentException("Place not found"));
    }
}