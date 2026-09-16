package com.fooddelivery.admin_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fooddelivery.admin_service.entity.Order;
import com.fooddelivery.admin_service.service.OrderService;

@RestController
@RequestMapping("/admin/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Create order
    @PostMapping
    public ResponseEntity<Order> createOrder(
            @RequestBody Order order) {

        return new ResponseEntity<>(
                orderService.createOrder(order),
                HttpStatus.CREATED);
    }

    // View all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {

        return ResponseEntity.ok(
                orderService.getAllOrders());
    }

    // View order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                orderService.getOrderById(id));
    }

    // Assign order to delivery person
    @PutMapping("/{orderId}/assign/{deliveryPersonId}")
    public ResponseEntity<Order> assignOrder(
            @PathVariable Long orderId,
            @PathVariable Long deliveryPersonId) {

        return ResponseEntity.ok(
                orderService.assignOrder(
                        orderId,
                        deliveryPersonId));
    }

    // Update order status
    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return ResponseEntity.ok(
                orderService.updateOrderStatus(id, status));
    }
}