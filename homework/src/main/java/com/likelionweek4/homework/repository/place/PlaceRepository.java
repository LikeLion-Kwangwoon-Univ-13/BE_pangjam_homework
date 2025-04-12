package com.likelionweek4.homework.repository.place;

import com.likelionweek4.homework.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long>, PlaceCustomRepository {
    List<Place> findByCategory(String category);
    List<Place> findByNameContainingIgnoreCase(String name);
    List<Place> findByCategoryGroup(String categoryGroup);
    List<Place> findAllByOrderByDistanceAsc();
    List<Place> findAllByOrderByDistanceDesc();
}
