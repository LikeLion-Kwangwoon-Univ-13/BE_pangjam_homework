package like_lion.pangjam.utility;

import like_lion.pangjam.domain.Place;
import like_lion.pangjam.domain.PlaceReview;
import like_lion.pangjam.dto.placeReview.PlaceReviewRequestDto;
import like_lion.pangjam.dto.placeReview.PlaceReviewResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PlaceReviewConverter {

    public static PlaceReview toEntity(PlaceReviewRequestDto requestDto, Place place) {
        return PlaceReview.builder()
                .nickname(NicknameGenerator.generateNickname())
                .place(place)
                .content(requestDto.getContent())
                .rating(requestDto.getRating())
                .build();
    }

    public static PlaceReviewResponseDto toPlaceReviewResponseDto(PlaceReview placeReview) {
        return PlaceReviewResponseDto.builder()
                .placeReviewId(placeReview.getPlaceReviewId())
                .rating(placeReview.getRating())
                .content(placeReview.getContent())
                .nickname(placeReview.getNickname())
                .createdAt(placeReview.getCreatedAt().toString())
                .build();
    }
}