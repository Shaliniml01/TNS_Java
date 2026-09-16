package com.fooddelivery.admin_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fooddelivery.admin_service.entity.Restaurant;
import com.fooddelivery.admin_service.repository.RestaurantRepository;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    public Restaurant updateRestaurant(Long id, Restaurant restaurant) {
        Restaurant existingRestaurant = restaurantRepository.findById(id).orElse(null);

        if (existingRestaurant != null) {
            existingRestaurant.setName(restaurant.getName());
            existingRestaurant.setLocation(restaurant.getLocation());
            existingRestaurant.setPhone(restaurant.getPhone());
            existingRestaurant.setEmail(restaurant.getEmail());

            return restaurantRepository.save(existingRestaurant);
        }

        return null;
    }

    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
}