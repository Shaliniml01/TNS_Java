package com.fooddelivery.admin_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fooddelivery.admin_service.entity.FoodItem;
import com.fooddelivery.admin_service.entity.Restaurant;
import com.fooddelivery.admin_service.repository.FoodItemRepository;
import com.fooddelivery.admin_service.repository.RestaurantRepository;

@Service
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;
    private final RestaurantRepository restaurantRepository;

    public FoodItemService(FoodItemRepository foodItemRepository,
                       RestaurantRepository restaurantRepository) {
    this.foodItemRepository = foodItemRepository;
    this.restaurantRepository = restaurantRepository;
    }

    public FoodItem saveFoodItem(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }

    public FoodItem getFoodItemById(Long id) {
        return foodItemRepository.findById(id).orElse(null);
    }

    public FoodItem updateFoodItem(Long id, FoodItem foodItem) {
        FoodItem existingFoodItem = foodItemRepository.findById(id).orElse(null);

        if (existingFoodItem != null) {
            existingFoodItem.setName(foodItem.getName());
            existingFoodItem.setDescription(foodItem.getDescription());
            existingFoodItem.setPrice(foodItem.getPrice());
            existingFoodItem.setCategory(foodItem.getCategory());
            existingFoodItem.setAvailable(foodItem.isAvailable());

            return foodItemRepository.save(existingFoodItem);
        }

        return null;
    }

    public void deleteFoodItem(Long id) {
        foodItemRepository.deleteById(id);
    }
    public FoodItem assignRestaurant(Long foodItemId, Long restaurantId) {

    FoodItem foodItem = foodItemRepository.findById(foodItemId).orElse(null);

    Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);

    if (foodItem != null && restaurant != null) {
        foodItem.setRestaurant(restaurant);
        return foodItemRepository.save(foodItem);
    }

    return null;
    }
}