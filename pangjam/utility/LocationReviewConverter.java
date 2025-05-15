package like_lion.pangjam.utility;

import like_lion.pangjam.domain.Location;
import like_lion.pangjam.domain.LocationReview;
import like_lion.pangjam.dto.locationReview.LocationReviewRequestDto;
import like_lion.pangjam.dto.locationReview.LocationReviewResponseDto;
import org.springframework.stereotype.Component;

@Component
public class LocationReviewConverter {

    public static LocationReview toEntity(LocationReviewRequestDto requestDto, Location location) {
        return LocationReview.builder()
                .locationReviewId(requestDto.getLocationId())
                .nickname(NicknameGenerator.generateNickname())
                .location(location)
                .content(requestDto.getContent())
                .rating(requestDto.getRating())
                .createdAt(requestDto.getCreatedAt())
                .build();
    }

    public static LocationReviewResponseDto toLocationReviewResponseDto(LocationReview locationReview) {
        return LocationReviewResponseDto.builder()
                .locationReviewId(locationReview.getLocationReviewId())
                .rating(locationReview.getRating())
                .content(locationReview.getContent())
                .nickname(locationReview.getNickname())
                .createdAt(locationReview.getCreatedAt().toString())
                .build();
    }
}