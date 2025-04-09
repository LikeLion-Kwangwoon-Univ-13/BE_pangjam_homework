package com.likelionweek4.homework.repository;

import com.likelionweek4.homework.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByCategory(String category);
    List<Restaurant> findByNameContainingIgnoreCase(String name);
    List<Restaurant> findByCategoryGroup(String categoryGroup);
    List<Restaurant> findAllByOrderByDistanceAsc();
    List<Restaurant> findAllByOrderByDistanceDesc();
}
