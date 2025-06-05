package com.likelionweek4.homework.service;

import com.likelionweek4.homework.dto.place.PlaceRequestDTO;
import com.likelionweek4.homework.dto.place.PlaceResponseDTO;
import com.likelionweek4.homework.entity.Place;
import com.likelionweek4.homework.repository.place.PlaceRepository;
import com.likelionweek4.homework.validator.place.SearchPlaceConditionValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceResponseDTO.SearchPlaceResult searchByCondition(PlaceRequestDTO.SearchPlaceConditionInfo requestDTO) {
        SearchPlaceConditionValidator.validate(requestDTO);
        Pageable pageable = PageRequest.of(requestDTO.getPage()-1, requestDTO.getSize());
        return new PlaceResponseDTO.SearchPlaceResult(placeRepository.findBySearchCondition(requestDTO,pageable));
    }

    public PlaceResponseDTO.PlaceInfo searchById(PlaceRequestDTO.PlaceIdDTO requestDTO) {
        return new PlaceResponseDTO.PlaceInfo(getPlace(requestDTO.getId()));
    }

    private Place getPlace(Long id) {
        Place place = placeRepository.findById(id).orElse(null);
        if(place == null) {
            throw new IllegalArgumentException("해당 식당이 존재하지 않습니다.");
        }
        return place;
    }
}
