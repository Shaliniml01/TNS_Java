package com.fooddelivery.admin_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fooddelivery.admin_service.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}