package like_lion.pangjam.dto.locationReview;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class LocationReviewResponseDto {
    private int locationReviewId;

    private int rating;
    private String content;
    private String nickname;
    private String createdAt;
}
