package like_lion.pangjam.service;

import like_lion.pangjam.domain.Place;
import like_lion.pangjam.domain.PlaceReview;
import like_lion.pangjam.dto.placeReview.PlaceReviewRequestDto;
import like_lion.pangjam.dto.placeReview.PlaceReviewResponseDto;
import like_lion.pangjam.repository.PlaceRepository;
import like_lion.pangjam.repository.PlaceReviewRepository;
import like_lion.pangjam.utility.PlaceReviewConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceReviewService {

    private final PlaceReviewRepository placeReviewRepository;
    private final PlaceRepository placeRepository;

    //리뷰 생성
    public PlaceReviewResponseDto createReview(PlaceReviewRequestDto placeReviewRequestDto)
    {
        Place place = placeRepository.findByPlaceId(placeReviewRequestDto.getPlaceId());
        PlaceReview placeReview = PlaceReviewConverter.toEntity(placeReviewRequestDto, place);
        PlaceReview savedReview = placeReviewRepository.save(placeReview);

        updateRatingStats(place, placeReview.getRating());

        return PlaceReviewConverter.toPlaceReviewResponseDto(savedReview);
    }

    //리뷰 전체 조회
    public Slice<PlaceReviewResponseDto> getPlaceReviews(long placeId, Pageable pageable)
    {
        Place place = placeRepository.findByPlaceId(placeId);
        Slice<PlaceReview> placeReviews = placeReviewRepository.findByPlace_PlaceId(placeId, pageable);
        return placeReviews.map(PlaceReviewConverter::toPlaceReviewResponseDto);
    }

    //reviewStats 업데이트
    private void updateRatingStats(Place place, int newRating) {
        List<Integer> counts = place.getRatingsCount();
        counts.set(newRating-1, counts.get(newRating-1)+1);

        int newReviewCount = place.getReviewCount()+1;
        place.setReviewCount(newReviewCount);

       //averageRating 업데이트
        double total = 0;
        for (int i = 0; i < 5; i++) {
            total += (i + 1) * counts.get(i);
        }
        double average = total / newReviewCount;

        place.setRatingsCount(counts);
        place.setReviewCount(newReviewCount);
        place.setAverageRating(average);
    }
}
