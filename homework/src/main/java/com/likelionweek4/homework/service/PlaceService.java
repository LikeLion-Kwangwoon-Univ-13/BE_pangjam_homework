package com.likelionweek4.homework.service;

import com.likelionweek4.homework.dto.place.PlaceRequestDTO;
import com.likelionweek4.homework.entity.Place;
import com.likelionweek4.homework.repository.PlaceRepository;
import com.likelionweek4.homework.validator.Place.CreatePlaceInfoValidator;
import com.likelionweek4.homework.validator.Place.UpdatePlaceInfoValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public Place create(PlaceRequestDTO.CreatePlaceInfo requestDTO) {
        CreatePlaceInfoValidator.validate(requestDTO);
        Place place = new Place(
                requestDTO.getName(),
                requestDTO.getAddress(),
                requestDTO.getPhone(),
                requestDTO.getDistance(),
                requestDTO.getLatitude(),
                requestDTO.getLongitude(),
                requestDTO.getCategoryGroup(),
                requestDTO.getCategory()
        );
        placeRepository.save(place);
        return place;
    }

    public List<Place> searchByCondition(PlaceRequestDTO.SearchPlaceConditionInfo requestDTO) {
        if (requestDTO.getName() != null) {
            return placeRepository.findByNameContainingIgnoreCase(requestDTO.getName());
        }
        if (requestDTO.getCategoryGroup() != null) {
            return placeRepository.findByCategoryGroup(requestDTO.getCategoryGroup());
        }
        if (requestDTO.getCategory() != null) {
            return placeRepository.findByCategory(requestDTO.getCategory());
        }
        if (requestDTO.getDistance() != null) {
            if (requestDTO.getDistance())
                return placeRepository.findAllByOrderByDistanceAsc();
            return placeRepository.findAllByOrderByDistanceDesc();
        }
        throw new IllegalArgumentException("검색 조건은 필수입니다.");
    }

    public Place searchById(PlaceRequestDTO.PlaceIdDTO requestDTO) {
        return getPlace(requestDTO.getId());
    }

    public Place update(PlaceRequestDTO.UpdatePlaceInfo requestDTO) {
        UpdatePlaceInfoValidator.validate(requestDTO);
        Place place = getPlace(requestDTO.getId());
        place.updateInfo(
                requestDTO.getName(),
                requestDTO.getAddress(),
                requestDTO.getDistance(),
                requestDTO.getLatitude(),
                requestDTO.getLongitude(),
                requestDTO.getCategory()
        );
        return placeRepository.save(place);
    }

    public void delete(PlaceRequestDTO.PlaceIdDTO requestDTO) {
        Place place = getPlace(requestDTO.getId());
        placeRepository.delete(place);
    }

    private Place getPlace(Long id) {
        Place place = placeRepository.findById(id).orElse(null);
        if(place == null) {
            throw new IllegalArgumentException("해당 식당이 존재하지 않습니다.");
        }
        return place;
    }

}
