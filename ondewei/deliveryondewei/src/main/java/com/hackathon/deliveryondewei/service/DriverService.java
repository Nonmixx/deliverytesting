package com.hackathon.deliveryondewei.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.deliveryondewei.model.Driver;
import com.hackathon.deliveryondewei.repository.DriverRepository;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    // Get driver by ID
    public Driver getDriverById(Long driverId) {
        return driverRepository.findById(driverId).orElseThrow(() -> new RuntimeException("Driver not found"));
    }

    // Driver authentication method
    public Optional<Driver> authenticateDriver(String username, Long phoneNumber, String password) {
        return driverRepository.findByUsernameAndPhoneNumberAndPassword(username, phoneNumber, password);
    }

    // Save new driver
    public Driver saveDriver(Driver driver) {
        return driverRepository.save(driver);
    }
}



