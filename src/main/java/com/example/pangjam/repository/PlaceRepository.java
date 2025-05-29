package com.example.pangjam.repository;

import com.example.pangjam.entity.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Page<Place> findByCategoryAndNameContaining(String category, String name, Pageable pageable);
    Page<Place> findByNameContaining(String name, Pageable pageable);
    Page<Place> findByCategory(String category, Pageable pageable);
    Page<Place> findAll(Pageable pageable);
}