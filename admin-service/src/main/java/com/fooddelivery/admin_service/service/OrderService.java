package com.fooddelivery.admin_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fooddelivery.admin_service.entity.DeliveryPerson;
import com.fooddelivery.admin_service.entity.Order;
import com.fooddelivery.admin_service.repository.DeliveryPersonRepository;
import com.fooddelivery.admin_service.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final DeliveryPersonRepository deliveryPersonRepository;

    public OrderService(OrderRepository orderRepository,
                        DeliveryPersonRepository deliveryPersonRepository) {
        this.orderRepository = orderRepository;
        this.deliveryPersonRepository = deliveryPersonRepository;
    }

    // Create an order
    public Order createOrder(Order order) {
        if (order.getStatus() == null || order.getStatus().isEmpty()) {
            order.setStatus("PLACED");
        }

        return orderRepository.save(order);
    }

    // View all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // View order by ID
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));
    }

    // Assign order to delivery person
    public Order assignOrder(Long orderId, Long deliveryPersonId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        DeliveryPerson deliveryPerson =
                deliveryPersonRepository.findById(deliveryPersonId)
                .orElseThrow(() ->
                        new RuntimeException("Delivery person not found"));

        if (!deliveryPerson.isAvailable()) {
            throw new RuntimeException(
                    "Delivery person is not available");
        }

        order.setDeliveryPerson(deliveryPerson);
        order.setStatus("ASSIGNED");

        deliveryPerson.setAvailable(false);
        deliveryPersonRepository.save(deliveryPerson);

        return orderRepository.save(order);
    }

    // Update order status
    public Order updateOrderStatus(Long id, String status) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        order.setStatus(status);

        return orderRepository.save(order);
    }
}