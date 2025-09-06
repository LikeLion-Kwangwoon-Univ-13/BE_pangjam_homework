package com.likelionweek4.homework.repository.place;

import com.likelionweek4.homework.dto.place.PlaceRequestDTO;
import com.likelionweek4.homework.entity.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlaceCustomRepository {
//    List<Place> findBySearchCondition(PlaceRequestDTO.SearchPlaceConditionInfo searchPlaceConditionInfo);
    Page<Place> findBySearchCondition(PlaceRequestDTO.SearchPlaceConditionInfo searchPlaceConditionInfo, Pageable pageable);
}
