package com.fooddelivery.admin_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fooddelivery.admin_service.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}