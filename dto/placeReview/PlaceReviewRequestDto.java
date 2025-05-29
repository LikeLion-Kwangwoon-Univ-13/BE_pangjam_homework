package like_lion.pangjam.dto.placeReview;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class PlaceReviewRequestDto {

    private long placeId;

    private int rating;
    private String content;
}
