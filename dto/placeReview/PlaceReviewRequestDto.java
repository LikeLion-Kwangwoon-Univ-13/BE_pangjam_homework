package like_lion.pangjam.dto.placeReview;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlaceReviewRequestDto {

    private int placeId;

    private int rating;
    private String content;
}
