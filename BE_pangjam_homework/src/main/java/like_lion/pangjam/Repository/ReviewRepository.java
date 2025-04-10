package like_lion.pangjam.Repository;

import like_lion.pangjam.Model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository <Review, Integer> {
    List<Review> findByLocationId(int locationId);
    List<Review> findTop3ByLocationIdOrderByCreatedAtDesc(int locationId);
    Page<Review> findByLocation_LocationId(int locationId, Pageable pageable);
}