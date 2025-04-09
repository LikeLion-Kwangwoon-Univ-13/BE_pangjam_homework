package com.likelionweek4.homework.controller;

import com.likelionweek4.homework.dto.MessageResponseDTO;
import com.likelionweek4.homework.dto.restaurant.RestaurantRequestDTO;
import com.likelionweek4.homework.dto.restaurant.RestaurantResponseDTO;
import com.likelionweek4.homework.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantResponseDTO.RestaurantInfo> createRestaurant(
            @RequestBody RestaurantRequestDTO.CreateRestaurantInfo requestDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new RestaurantResponseDTO.RestaurantInfo(restaurantService.create(requestDTO)));
    }

    @GetMapping()
    public ResponseEntity<RestaurantResponseDTO.SearchRestaurantResult> searchRestaurantInfo(
            @ModelAttribute RestaurantRequestDTO.SearchRestaurantConditionInfo requestDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new RestaurantResponseDTO.SearchRestaurantResult(restaurantService.searchByCondition(requestDTO)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO.RestaurantInfo> searchRestaurantById(
            @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(new RestaurantResponseDTO.RestaurantInfo(restaurantService.searchById(new RestaurantRequestDTO.RestaurantIdDTO(id))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO.RestaurantInfo> updateRestaurant(
            @PathVariable Long id,
            @RequestBody RestaurantRequestDTO.UpdateRestaurantInfo requestDTO) {
        requestDTO.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new RestaurantResponseDTO.RestaurantInfo(restaurantService.update(requestDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDTO.Message> deleteRestaurant(
            @PathVariable Long id) {
        restaurantService.delete(new RestaurantRequestDTO.RestaurantIdDTO(id));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessageResponseDTO.Message("식당 정보가 삭제되었습니다."));
    }
}
