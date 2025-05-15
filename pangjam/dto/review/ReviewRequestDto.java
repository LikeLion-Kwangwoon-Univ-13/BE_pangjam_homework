package like_lion.pangjam.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReviewRequestDto {
    private int reviewId;

    private int rating;
    private String content;
    private LocalDateTime createdAt;
}
