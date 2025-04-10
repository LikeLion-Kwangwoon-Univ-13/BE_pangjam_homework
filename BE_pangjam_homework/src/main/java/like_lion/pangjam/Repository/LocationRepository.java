package like_lion.pangjam.Repository;

import like_lion.pangjam.Model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    List<Location> findByCategoryGroupName(String categoryGroupName);
    List<Location> findByPlaceNameContaining(String placeName);
    List<Location> findByCategoryName(String categoryName);
    Optional<Location> findByLocationId(int locationId);
}
