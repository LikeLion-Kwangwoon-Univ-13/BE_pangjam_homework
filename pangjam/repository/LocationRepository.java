package like_lion.pangjam.repository;

import like_lion.pangjam.domain.Location;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    List<Location> findByNameContaining(String name);
    List<Location> findAllByCategory(String category);
    Slice<Location> findByLocationIdLessThan(int lastLocationId, Pageable pageable);
    Location findByLocationId(int locationId);
}
