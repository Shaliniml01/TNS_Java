package com.fooddelivery.admin_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fooddelivery.admin_service.entity.DeliveryPerson;

public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, Long> {

}