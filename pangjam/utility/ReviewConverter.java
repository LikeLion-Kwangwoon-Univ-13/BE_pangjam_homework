package like_lion.pangjam.utility;

import like_lion.pangjam.domain.Review;
import like_lion.pangjam.dto.review.ReviewRequestDto;
import like_lion.pangjam.dto.review.ReviewResponseDto;

public class ReviewConverter {

    public static Review toEntity(ReviewRequestDto requestDto) {
        return Review.builder()
                .reviewId(requestDto.getReviewId())
                .nickname(NicknameGenerator.generateNickname())
                .content(requestDto.getContent())
                .rating(requestDto.getRating())
                .createdAt(requestDto.getCreatedAt())
                .build();
    }

    public static ReviewResponseDto toReviewResponseDto(Review review) {
        return ReviewResponseDto.builder()
                .reviewId(review.getReviewId())
                .nickname(review.getNickname())
                .rating(review.getReviewId())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
