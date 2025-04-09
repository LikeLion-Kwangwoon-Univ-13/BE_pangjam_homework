package com.likelionweek4.homework.service;

import com.likelionweek4.homework.dto.restaurant.RestaurantRequestDTO;
import com.likelionweek4.homework.entity.Restaurant;
import com.likelionweek4.homework.repository.RestaurantRepository;
import com.likelionweek4.homework.validator.restaurant.CreateRestaurantInfoValidator;
import com.likelionweek4.homework.validator.restaurant.UpdateRestaurantInfoValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Restaurant create(RestaurantRequestDTO.CreateRestaurantInfo requestDTO) {
        CreateRestaurantInfoValidator.validate(requestDTO);
        Restaurant restaurant = new Restaurant(
                requestDTO.getName(),
                requestDTO.getAddress(),
                requestDTO.getPhone(),
                requestDTO.getDistance(),
                requestDTO.getLatitude(),
                requestDTO.getLongitude(),
                requestDTO.getCategoryGroup(),
                requestDTO.getCategory()
        );
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    public List<Restaurant> searchByCondition(RestaurantRequestDTO.SearchRestaurantConditionInfo requestDTO) {
        if (requestDTO.getName() != null) {
            return restaurantRepository.findByNameContainingIgnoreCase(requestDTO.getName());
        }
        if (requestDTO.getCategoryGroup() != null) {
            return restaurantRepository.findByCategoryGroup(requestDTO.getCategoryGroup());
        }
        if (requestDTO.getCategory() != null) {
            return restaurantRepository.findByCategory(requestDTO.getCategory());
        }
        if (requestDTO.getDistance() != null) {
            if (requestDTO.getDistance() == 0)
                return restaurantRepository.findAllByOrderByDistanceAsc();
            else if(requestDTO.getDistance()== 1)
                return restaurantRepository.findAllByOrderByDistanceDesc();
            throw new IllegalArgumentException("distance 파라미터값이 잘못되었습니다. 0: 오름차순, 1: 내림차순");
        }
        throw new IllegalArgumentException("검색 조건은 필수입니다.");
    }

    public  Restaurant searchById(RestaurantRequestDTO.RestaurantIdDTO requestDTO) {
        return getRestaurant(requestDTO.getId());
    }

    public Restaurant update(RestaurantRequestDTO.UpdateRestaurantInfo requestDTO) {
        UpdateRestaurantInfoValidator.validate(requestDTO);
        Restaurant restaurant = getRestaurant(requestDTO.getId());
        restaurant.updateInfo(
                requestDTO.getName(),
                requestDTO.getAddress(),
                requestDTO.getDistance(),
                requestDTO.getLatitude(),
                requestDTO.getLongitude(),
                requestDTO.getCategory()
        );
        return restaurantRepository.save(restaurant);
    }

    public void delete(RestaurantRequestDTO.RestaurantIdDTO requestDTO) {
        Restaurant restaurant = getRestaurant(requestDTO.getId());
        restaurantRepository.delete(restaurant);
    }

    private Restaurant getRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        if(restaurant == null) {
            throw new IllegalArgumentException("해당 식당이 존재하지 않습니다.");
        }
        return restaurant;
    }

}
