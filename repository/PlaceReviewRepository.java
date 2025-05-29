package like_lion.pangjam.repository;

import like_lion.pangjam.domain.PlaceReview;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlaceReviewRepository extends JpaRepository<PlaceReview, Integer> {

    List<PlaceReview> findTop5ByPlace_PlaceIdOrderByCreatedAtDesc(long placeId);
    Slice<PlaceReview> findByPlace_PlaceId(long placeId);
}
