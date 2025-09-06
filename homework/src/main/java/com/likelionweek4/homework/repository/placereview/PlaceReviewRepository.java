package com.likelionweek4.homework.repository.placereview;

import com.likelionweek4.homework.entity.PlaceReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceReviewRepository extends JpaRepository<PlaceReview, Long> {
//    List<Review> findByPlace_placeId(Long placeId, Sort sort);
    Page<PlaceReview> findByPlace_placeId(Long placeId, Pageable pageable);
    @Query("SELECT round(AVG(r.rating),2) FROM PlaceReview r WHERE r.place.placeId = :placeId")
    Double findAverageRatingByPlaceId(@Param("placeId") Long placeID);

}
