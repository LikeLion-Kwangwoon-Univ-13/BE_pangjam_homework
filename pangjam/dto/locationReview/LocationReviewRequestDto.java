package like_lion.pangjam.dto.locationReview;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class LocationReviewRequestDto {

    private int locationId;

    private int rating;
    private String content;
    private LocalDateTime createdAt;
}
