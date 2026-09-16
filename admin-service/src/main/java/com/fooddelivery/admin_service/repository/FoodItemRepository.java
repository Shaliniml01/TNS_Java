package com.fooddelivery.admin_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fooddelivery.admin_service.entity.FoodItem;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

}