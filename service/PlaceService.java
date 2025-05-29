package like_lion.pangjam.service;

import like_lion.pangjam.domain.Place;
import like_lion.pangjam.domain.PlaceReview;
import like_lion.pangjam.dto.place.PlaceResponseDto;
import like_lion.pangjam.repository.PlaceRepository;
import like_lion.pangjam.repository.PlaceReviewRepository;
import like_lion.pangjam.utility.PlaceConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceReviewRepository placeReviewRepository;

    //place 리스트 조회
    public Slice<PlaceResponseDto.PlaceInfo> getPlaces(String name, String category, Pageable pageable) {
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Order.asc("name"))
        );

        Slice<Place> places = placeRepository.findByNameContainingAndCategory(
                name,
                category,
                sortedPageable
        );

        return places.map(PlaceConverter::toPlaceInfo);
    }

    //상세정보 조회
    public PlaceResponseDto.Detail getPlaceDetail(long placeId) {
        Place place = placeRepository.findByPlaceId(placeId);

        List<PlaceReview> recentReviews = placeReviewRepository.findTop5ByPlace_PlaceIdOrderByCreatedAtDesc(placeId);
        List<PlaceReview> allReviews = place.getPlaceReviews();

        return PlaceResponseDto.Detail.from(place, recentReviews, allReviews);
    }
}