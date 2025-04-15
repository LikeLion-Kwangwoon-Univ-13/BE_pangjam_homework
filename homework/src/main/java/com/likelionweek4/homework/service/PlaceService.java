package com.likelionweek4.homework.service;

import com.likelionweek4.homework.dto.MessageResponseDTO;
import com.likelionweek4.homework.dto.place.PlaceRequestDTO;
import com.likelionweek4.homework.dto.place.PlaceResponseDTO;
import com.likelionweek4.homework.entity.Place;
import com.likelionweek4.homework.repository.place.PlaceRepository;
import com.likelionweek4.homework.validator.place.CreatePlaceInfoValidator;
import com.likelionweek4.homework.validator.place.SearchPlaceConditionValidator;
import com.likelionweek4.homework.validator.place.UpdatePlaceInfoValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceResponseDTO.PlaceInfo create(PlaceRequestDTO.CreatePlaceInfo requestDTO) {
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
        return new PlaceResponseDTO.PlaceInfo(place);
    }

    public PlaceResponseDTO.SearchPlaceResult searchByCondition(PlaceRequestDTO.SearchPlaceConditionInfo requestDTO) {
        SearchPlaceConditionValidator.validate(requestDTO);
        Pageable pageable = PageRequest.of(requestDTO.getPage()-1, requestDTO.getSize());
        return new PlaceResponseDTO.SearchPlaceResult(placeRepository.findBySearchCondition(requestDTO, pageable), pageable);
    }

    public PlaceResponseDTO.PlaceInfo searchById(PlaceRequestDTO.PlaceIdDTO requestDTO) {
        return new PlaceResponseDTO.PlaceInfo(getPlace(requestDTO.getId()));
    }

    public PlaceResponseDTO.PlaceInfo update(PlaceRequestDTO.UpdatePlaceInfo requestDTO) {
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
        return new PlaceResponseDTO.PlaceInfo(placeRepository.save(place));
    }

    public MessageResponseDTO.Message delete(PlaceRequestDTO.PlaceIdDTO requestDTO) {
        Place place = getPlace(requestDTO.getId());
        placeRepository.delete(place);
        return new MessageResponseDTO.Message("식당 정보가 삭제되었습니다.");
    }

    private Place getPlace(Long id) {
        Place place = placeRepository.findById(id).orElse(null);
        if(place == null) {
            throw new IllegalArgumentException("해당 식당이 존재하지 않습니다.");
        }
        return place;
    }
}
