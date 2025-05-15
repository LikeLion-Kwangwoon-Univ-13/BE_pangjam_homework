package like_lion.pangjam.repository;

import like_lion.pangjam.domain.LocationReview;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface LocationReviewRepository extends JpaRepository<LocationReview, Integer> {

    List<LocationReview> findTop5ByLocation_LocationIdOrderByCreatedAtDesc(int locationId);
    Slice<LocationReview> findByLocation_LocationId(int locationId);
}
