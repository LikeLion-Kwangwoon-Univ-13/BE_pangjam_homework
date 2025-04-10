package like_lion.pangjam.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int locationId;

    private String addressName;
    private String roadAddressName;
    private String categoryGroupName;
    private String categoryName;
    private String placeName;
    private double averageRating;
    private String phone;
    private double latitude;
    private double longitude;
    private String distance;
    //"image_url" : "String"

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

}
