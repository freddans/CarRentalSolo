package com.example.demo.controller;

import com.example.demo.entity.Car;
import com.example.demo.service.CarService;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class CustomerController {

  private CustomerService customerService;
  private CarService carService;

  @Autowired
  public CustomerController(CustomerService customerService, CarService carService) {
    this.customerService = customerService;
    this.carService = carService;
  }

  // TODO Lista alla tillgängliga bilar
  @GetMapping("/cars")
  public ResponseEntity<List<Car>> getAllCars() {
    return ResponseEntity.ok(carService.getAllAvailableCars());
  }

  // TODO Book a car POST
  @PostMapping("/ordercar")
  public void bookCar() {
    // TODO using @RequestBody to get id since its sent inside the body.
  }

  // TODO Cancel order
  @PutMapping("/cancelorder")
  public void cancelBooking() {
    // TODO using @RequestBody to get id since its sent inside the body.
  }

  // TODO View former and and active bookings
  @GetMapping("/myorders")
  public void viewFormerAndActiveBookings() {

  }
}
