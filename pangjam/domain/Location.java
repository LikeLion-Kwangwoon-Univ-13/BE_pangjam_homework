package like_lion.pangjam.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int locationId;

    private String category;
    private String name;
    private double averageRating;
    private String phone;
    private String address;
    private String distance;
    private double latitude;
    private double longitude;
    private String thumbNail;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private List<LocationReview> locationReviews;

}