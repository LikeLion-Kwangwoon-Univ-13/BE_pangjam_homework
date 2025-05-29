package like_lion.pangjam.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Arrays;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long placeId;

    private String category;
    private String name;
    private double averageRating;
    private String phone;
    private String address;
    private String distance;
    private double latitude;
    private double longitude;
    private String thumbNail;
    private int reviewCount;

    @ElementCollection
    @CollectionTable(name = "place_ratings_counts", joinColumns = @JoinColumn(name = "place_id"))
    @Column(name = "count")
    private List<Integer> ratingsCount = Arrays.asList(0,0,0,0,0);

    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY)
    private List<PlaceReview> placeReviews;

}