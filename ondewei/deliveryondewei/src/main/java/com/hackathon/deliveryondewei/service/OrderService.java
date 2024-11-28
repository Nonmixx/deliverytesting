package com.hackathon.deliveryondewei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.deliveryondewei.model.Driver;
import com.hackathon.deliveryondewei.model.Order;
import com.hackathon.deliveryondewei.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Get all available orders
    public List<Order> getAvailableOrders() {
        return orderRepository.findByOrderStatus("available");
    }

    // Get order by ID
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order saveOrder(Order order) {
        // Calculate the delivery fee based on distance before saving the order
        Double deliveryFee = calculateDeliveryFee(order.getDistance());
        order.setDeliveryFee(deliveryFee);

        return orderRepository.save(order);
    }

    // Assign order to a driver and calculate delivery fee
    public Order assignOrderToDriver(Order order, Driver driver) {
        order.setDriver(driver);  // Assign the driver
        order.setOrderStatus("picked_up");  // Update status to "picked_up"
        order.setDeliveryFee(calculateDeliveryFee(order.getDistance()));  // Calculate fee
        return orderRepository.save(order);  // Save updated order
    }

    // Update the order status
    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }

    // Calculate the delivery fee (RM 1 for every 200 meters)
    public Double calculateDeliveryFee(Integer distance) {
        return (distance / 200) * 1.0;  // RM 1 for every 200 meters
    }
}



