package com.hackathon.deliveryondewei.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.deliveryondewei.model.Driver;
import com.hackathon.deliveryondewei.model.Order;
import com.hackathon.deliveryondewei.service.DriverService;
import com.hackathon.deliveryondewei.service.OrderService;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private OrderService orderService;

    // New driver register
    @PostMapping("/register")
    public Driver registerDriver(@RequestBody Driver driver) {
        return driverService.saveDriver(driver);
    }

    // Driver login
    @PostMapping("/login")
    public Driver loginDriver(@RequestParam String username, @RequestParam Long phoneNumber, @RequestParam String password) {
        Optional<Driver> driver = driverService.authenticateDriver(username, phoneNumber, password);
        if (driver.isPresent()) {
            return driver.get();
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    // Get available orders
    @GetMapping("/orders/available")
    public List<Order> getAvailableOrders() {
        return orderService.getAvailableOrders();
    }

    // Driver chooses an order and updates its status to "picked_up"
    @PostMapping("/orders/{orderId}/pick-up")
    public Order pickUpOrder(@PathVariable Long orderId, @RequestParam Long driverId) {
        Driver driver = driverService.getDriverById(driverId); // Get driver by ID
        Order order = orderService.getOrderById(orderId); // Get the order by ID
        return orderService.assignOrderToDriver(order, driver);  // Assign the order to the driver and save
    }

    // Update order status (e.g., on the way, delivered)
    @PostMapping("/orders/{orderId}/status")
    public Order updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        return orderService.updateOrderStatus(orderId, status);
    }
}


