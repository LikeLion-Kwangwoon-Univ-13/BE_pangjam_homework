package like_lion.pangjam.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "location_reviews")
public class LocationReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int locationReviewId;

    private int rating;
    private String content;
    private String nickname;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
}
