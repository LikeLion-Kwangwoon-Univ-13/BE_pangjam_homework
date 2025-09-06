package com.likelionweek4.homework.controller;

import com.likelionweek4.homework.dto.MessageResponseDTO;
import com.likelionweek4.homework.dto.place.PlaceRequestDTO;
import com.likelionweek4.homework.dto.place.PlaceResponseDTO;
import com.likelionweek4.homework.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService PlaceService;

    @GetMapping("/page/{page}")
    public ResponseEntity<PlaceResponseDTO.SearchPlaceResult> searchPlaceInfos(
            @PathVariable("page") int page,
            @ModelAttribute PlaceRequestDTO.SearchPlaceConditionInfo requestDTO) {
        requestDTO.setPage(page);
        return ResponseEntity.status(HttpStatus.OK).body(PlaceService.searchByCondition(requestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaceResponseDTO.PlaceInfo> searchPlaceById(
            @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(PlaceService.searchById(new PlaceRequestDTO.PlaceIdDTO(id)));
    }

}
