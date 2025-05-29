package like_lion.pangjam.service;

import like_lion.pangjam.domain.Place;
import like_lion.pangjam.domain.PlaceReview;
import like_lion.pangjam.dto.global.SliceResponseDto;
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
    public SliceResponseDto<PlaceResponseDto.PlaceInfo> searchPlacesInfos(
            String name, String category, int page, int size
    ) {
        Pageable sortedPageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Order.asc("name"))
        );

        Slice<Place> places = placeRepository.findByNameContainingAndCategory(
                name,
                category,
                sortedPageable
        );

        Slice<PlaceResponseDto.PlaceInfo> mapped = places.map(PlaceConverter::toPlaceInfo);

        return SliceResponseDto.of(
                mapped.getContent(),
                mapped.hasNext(),
                mapped.getNumber(),
                mapped.getSize()
        );
    }

    //상세정보 조회
    public PlaceResponseDto.Detail getPlaceDetail(long placeId) {
        Place place = placeRepository.findByPlaceId(placeId);

        List<PlaceReview> recentReviews = placeReviewRepository.findTop5ByPlace_PlaceIdOrderByCreatedAtDesc(placeId);
        List<PlaceReview> allReviews = place.getPlaceReviews();

        return PlaceResponseDto.Detail.from(place, recentReviews, allReviews);
    }
}
