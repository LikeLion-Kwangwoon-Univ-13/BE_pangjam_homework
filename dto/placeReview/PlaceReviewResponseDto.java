package like_lion.pangjam.dto.placeReview;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class PlaceReviewResponseDto {
    private long placeReviewId;

    private int rating;
    private String content;
    private String nickname;
    private String createdAt;
}
