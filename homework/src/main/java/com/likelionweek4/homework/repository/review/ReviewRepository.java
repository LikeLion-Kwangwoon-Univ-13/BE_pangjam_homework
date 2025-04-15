package com.likelionweek4.homework.repository.review;

import com.likelionweek4.homework.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
//    List<Review> findByPlace_placeId(Long placeId, Sort sort);
    Page<Review> findByPlace_placeId(Long placeId, Pageable pageable);
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.place.placeId = :placeId")
    Double findAverageRatingByPlaceId(@Param("placeId") Long placeID);

}
