package com.fooddelivery.admin_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.admin_service.entity.FoodItem;
import com.fooddelivery.admin_service.service.FoodItemService;

@RestController
@RequestMapping("/fooditem")
public class FoodItemController {

    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @PostMapping
    public FoodItem createFoodItem(@RequestBody FoodItem foodItem) {
        return foodItemService.saveFoodItem(foodItem);
    }

    @GetMapping
    public List<FoodItem> getAllFoodItems() {
        return foodItemService.getAllFoodItems();
    }

    @GetMapping("/{id}")
    public FoodItem getFoodItemById(@PathVariable Long id) {
        return foodItemService.getFoodItemById(id);
    }

    @PutMapping("/{id}")
    public FoodItem updateFoodItem(
            @PathVariable Long id,
            @RequestBody FoodItem foodItem) {

        return foodItemService.updateFoodItem(id, foodItem);
    }

    @DeleteMapping("/{id}")
    public String deleteFoodItem(@PathVariable Long id) {
        foodItemService.deleteFoodItem(id);
        return "Food item deleted successfully";
    }
    @PutMapping("/{foodItemId}/restaurant/{restaurantId}")
    public FoodItem assignRestaurant(
        @PathVariable Long foodItemId,
        @PathVariable Long restaurantId) {

    return foodItemService.assignRestaurant(foodItemId, restaurantId);
    }
}
