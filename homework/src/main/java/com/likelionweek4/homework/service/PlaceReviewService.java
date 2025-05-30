package com.likelionweek4.homework.service;

import com.likelionweek4.homework.dto.placereview.PlaceReviewRequestDTO;
import com.likelionweek4.homework.dto.placereview.PlaceReviewResponseDTO;
import com.likelionweek4.homework.entity.Place;
import com.likelionweek4.homework.entity.PlaceRating;
import com.likelionweek4.homework.entity.PlaceReview;
import com.likelionweek4.homework.repository.place.PlaceRepository;
import com.likelionweek4.homework.repository.placerating.PlaceRatingRepository;
import com.likelionweek4.homework.repository.placereview.PlaceReviewRepository;
import com.likelionweek4.homework.validator.placereview.CreatePlaceReviewInfoValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.util.Collections.sort;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaceReviewService {

    private final PlaceRepository placeRepository;
    private final PlaceReviewRepository placeReviewRepository;
    private final PlaceRatingRepository placeRatingRepository;

    public PlaceReviewResponseDTO.ReviewInfo create(PlaceReviewRequestDTO.CreateReviewInfo requestDTO) {
        CreatePlaceReviewInfoValidator.validate(requestDTO);
        Place place = getPlace(requestDTO.getPlaceId());
        PlaceReview placeReview = new PlaceReview(
                place,
                requestDTO.getRating(),
                requestDTO.getComment(),
                LocalDateTime.now()
        );
        placeReviewRepository.save(placeReview);
        placeRatingUpdate(requestDTO, place);
        return new PlaceReviewResponseDTO.ReviewInfo(placeReview);
    }

    private void placeRatingUpdate(PlaceReviewRequestDTO.CreateReviewInfo requestDTO, Place place) {
        PlaceRating placeRating = placeRatingRepository.findByPlace_placeId(place.getPlaceId());
        if(placeRating != null) {
            placeRating.updateRating(requestDTO.getRating());
        }
        else {
            placeRating = new PlaceRating(place);
            placeRating.updateRating(requestDTO.getRating());
        }
        placeRatingRepository.save(placeRating);
        updatePlaceAverageRating(place, placeRating);
    }

    private void updatePlaceAverageRating(Place place, PlaceRating placeRating) {
        int ratingOne = placeRating.getOne();
        int ratingTwo = placeRating.getTwo();
        int ratingThree = placeRating.getThree();
        int ratingFour = placeRating.getFour();
        int ratingFive = placeRating.getFive();
        double averageRating = (ratingOne + ratingTwo*2 + ratingThree*3 + ratingFour*4 + ratingFive*5)/(double)(ratingOne + ratingTwo + ratingThree + ratingFour + ratingFive);
        place.updateAverageRating(averageRating);
        placeRepository.save(place);
    }

    public PlaceReviewResponseDTO.SearchReviewsResult searchReviewByPlaceId(PlaceReviewRequestDTO.SearchReviewsInfo requestDTO) {
        return new PlaceReviewResponseDTO.SearchReviewsResult(getReviewList(requestDTO));
    }

    private Page<PlaceReview> getReviewList(PlaceReviewRequestDTO.SearchReviewsInfo requestDTO) {
        Long placeId = requestDTO.getPlaceId();
        String sortBy = requestDTO.getSortBy();
        if(sortBy.equals("latest")) {
            Pageable pageable = PageRequest.of(requestDTO.getPage()-1, requestDTO.getSize(), Sort.by(Sort.Direction.DESC,"createdAt"));
            return placeReviewRepository.findByPlace_placeId(placeId, pageable);
        }
        else if(sortBy.equals("rating")) {
            Pageable pageable = PageRequest.of(requestDTO.getPage()-1, requestDTO.getSize(), Sort.by(Sort.Direction.DESC,"rating"));
            return placeReviewRepository.findByPlace_placeId(placeId, pageable);
        }
        throw new IllegalArgumentException("리뷰 조회 정렬 기준이 없습니다. (latest, oldest, lowRating, highRating)");
    }

    private Place getPlace(Long placeId) {
        Place place = placeRepository.findById(placeId).orElse(null);
        if(place == null) {
            throw new IllegalArgumentException("해당 식당이 존재하지 않습니다.");
        }
        return place;
    }
}
