package com.hackathon.deliveryondewei.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pickupLocation;
    private String deliveryLocation;
    private Integer distance; // In meters
    private String orderStatus; // "available", "picked_up", "on_the_way", "delivered"
    private Double deliveryFee; // Calculated fee based on distance
    private String userPhoneNumber; // Phone number of the user

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver; // Driver assigned to the order

    public Order() {
    }

    public Order(String pickupLocation, String deliveryLocation, Integer distance, String orderStatus, String userPhoneNumber) {
        this.pickupLocation = pickupLocation;
        this.deliveryLocation = deliveryLocation;
        this.distance = distance;
        this.orderStatus = orderStatus;
        this.userPhoneNumber = userPhoneNumber;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}