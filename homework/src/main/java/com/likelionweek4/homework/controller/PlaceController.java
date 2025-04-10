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
@RequestMapping("/Places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService PlaceService;

    @PostMapping
    public ResponseEntity<PlaceResponseDTO.PlaceInfo> createPlace(
            @RequestBody PlaceRequestDTO.CreatePlaceInfo requestDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new PlaceResponseDTO.PlaceInfo(PlaceService.create(requestDTO)));
    }

    @GetMapping()
    public ResponseEntity<PlaceResponseDTO.SearchPlaceResult> searchPlaceInfo(
            @ModelAttribute PlaceRequestDTO.SearchPlaceConditionInfo requestDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new PlaceResponseDTO.SearchPlaceResult(PlaceService.searchByCondition(requestDTO)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaceResponseDTO.PlaceInfo> searchPlaceById(
            @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(new PlaceResponseDTO.PlaceInfo(PlaceService.searchById(new PlaceRequestDTO.PlaceIdDTO(id))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlaceResponseDTO.PlaceInfo> updatePlace(
            @PathVariable Long id,
            @RequestBody PlaceRequestDTO.UpdatePlaceInfo requestDTO) {
        requestDTO.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new PlaceResponseDTO.PlaceInfo(PlaceService.update(requestDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDTO.Message> deletePlace(
            @PathVariable Long id) {
        PlaceService.delete(new PlaceRequestDTO.PlaceIdDTO(id));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessageResponseDTO.Message("식당 정보가 삭제되었습니다."));
    }
}
