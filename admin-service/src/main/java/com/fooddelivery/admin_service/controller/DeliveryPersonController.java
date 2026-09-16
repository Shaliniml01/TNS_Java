package com.fooddelivery.admin_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fooddelivery.admin_service.entity.DeliveryPerson;
import com.fooddelivery.admin_service.service.DeliveryPersonService;

@RestController
@RequestMapping("/delivery-persons")
public class DeliveryPersonController {

    private final DeliveryPersonService service;

    public DeliveryPersonController(DeliveryPersonService service) {
        this.service = service;
    }

    // Add delivery person
    @PostMapping
    public ResponseEntity<DeliveryPerson> addDeliveryPerson(
            @RequestBody DeliveryPerson deliveryPerson) {

        return new ResponseEntity<>(
                service.addDeliveryPerson(deliveryPerson),
                HttpStatus.CREATED);
    }

    // Get all delivery persons
    @GetMapping
    public ResponseEntity<List<DeliveryPerson>> getAllDeliveryPersons() {

        return ResponseEntity.ok(
                service.getAllDeliveryPersons());
    }

    // Get delivery person by ID
    @GetMapping("/{id}")
    public ResponseEntity<DeliveryPerson> getDeliveryPersonById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getDeliveryPersonById(id));
    }

    // Update delivery person
    @PutMapping("/{id}")
    public ResponseEntity<DeliveryPerson> updateDeliveryPerson(
            @PathVariable Long id,
            @RequestBody DeliveryPerson deliveryPerson) {

        return ResponseEntity.ok(
                service.updateDeliveryPerson(id, deliveryPerson));
    }

    // Delete delivery person
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDeliveryPerson(
            @PathVariable Long id) {

        service.deleteDeliveryPerson(id);

        return ResponseEntity.ok("Delivery person deleted successfully");
    }
}