package com.likelionweek4.homework.repository.placerating;

import com.likelionweek4.homework.entity.PlaceRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlaceRatingRepository extends JpaRepository<PlaceRating, Long> {
    PlaceRating findByPlace_placeId(Long placeId);
}
