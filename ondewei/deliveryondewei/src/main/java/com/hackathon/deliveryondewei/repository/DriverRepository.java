package com.hackathon.deliveryondewei.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.deliveryondewei.model.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByUsernameAndPhoneNumberAndPassword(String username, Long phoneNumber, String password);
}
