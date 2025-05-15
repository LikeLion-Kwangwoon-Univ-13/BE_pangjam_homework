package like_lion.pangjam.service;

import like_lion.pangjam.domain.Location;
import like_lion.pangjam.domain.LocationReview;
import like_lion.pangjam.dto.locationReview.LocationReviewRequestDto;
import like_lion.pangjam.dto.locationReview.LocationReviewResponseDto;
import like_lion.pangjam.repository.LocationRepository;
import like_lion.pangjam.repository.LocationReviewRepository;
import like_lion.pangjam.utility.LocationReviewConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationReviewService {

    private final LocationReviewRepository locationReviewRepository;
    private final LocationRepository locationRepository;

    //리뷰 작성
    public LocationReviewResponseDto createReview(LocationReviewRequestDto locationReviewRequestDto)
    {
        Location location = locationRepository.findByLocationId(locationReviewRequestDto.getLocationId());

        LocationReview locationReview = LocationReviewConverter.toEntity(locationReviewRequestDto, location);
        LocationReview savedReview = locationReviewRepository.save(locationReview);
        updateAverageRating(location.getLocationId());

        return LocationReviewConverter.toLocationReviewResponseDto(savedReview);
    }

    //리뷰 전체 조회
    public Slice<LocationReviewResponseDto> getLocationReviews(int locationId, Pageable pageable)
    {
        Location location = locationRepository.findByLocationId(locationId);
        Slice<LocationReview> locationReviews = locationReviewRepository.findByLocation_LocationId(locationId);
        return locationReviews.map(LocationReviewConverter::toLocationReviewResponseDto);
    }



    //리뷰 평균 별점 업데이트
    private void updateAverageRating(int locationId) {
        locationRepository.findById(locationId)
            .ifPresent(location -> {
                double averageRating = locationReviewRepository.findByLocation_LocationId(locationId)
                        .stream()
                        .mapToInt(LocationReview::getRating)
                        .average()
                        .orElse(0.0);

                location.setAverageRating(averageRating);
                locationRepository.save(location);
            });
    }
}