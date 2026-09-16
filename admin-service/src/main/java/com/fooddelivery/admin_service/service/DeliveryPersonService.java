package com.fooddelivery.admin_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fooddelivery.admin_service.entity.DeliveryPerson;
import com.fooddelivery.admin_service.repository.DeliveryPersonRepository;

@Service
public class DeliveryPersonService {

    private final DeliveryPersonRepository repository;

    public DeliveryPersonService(DeliveryPersonRepository repository) {
        this.repository = repository;
    }

    // Add delivery person
    public DeliveryPerson addDeliveryPerson(DeliveryPerson deliveryPerson) {
        return repository.save(deliveryPerson);
    }

    // Get all delivery persons
    public List<DeliveryPerson> getAllDeliveryPersons() {
        return repository.findAll();
    }

    // Get delivery person by ID
    public DeliveryPerson getDeliveryPersonById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery person not found"));
    }

    // Update delivery person
    public DeliveryPerson updateDeliveryPerson(Long id, DeliveryPerson updatedPerson) {

        DeliveryPerson existingPerson = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery person not found"));

        existingPerson.setName(updatedPerson.getName());
        existingPerson.setPhone(updatedPerson.getPhone());
        existingPerson.setAvailable(updatedPerson.isAvailable());

        return repository.save(existingPerson);
    }

    // Delete delivery person
    public void deleteDeliveryPerson(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Delivery person not found");
        }

        repository.deleteById(id);
    }
}