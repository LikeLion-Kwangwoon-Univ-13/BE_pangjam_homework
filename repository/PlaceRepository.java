package like_lion.pangjam.repository;

import like_lion.pangjam.domain.Place;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Place findByPlaceId(long placeId);
    Slice<Place> findByNameContainingAndCategory(String name, String category, Pageable sortedPageable);
}
