package like_lion.pangjam.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {
    private int reviewId;

    private int rating;
    private String content;
    private String nickname;
    private LocalDateTime createdAt;
}
