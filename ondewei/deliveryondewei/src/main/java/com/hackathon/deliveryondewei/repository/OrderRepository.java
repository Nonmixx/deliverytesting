package com.hackathon.deliveryondewei.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.deliveryondewei.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByOrderStatus(String orderStatus);
}
