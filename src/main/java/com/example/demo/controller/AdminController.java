package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/")
public class AdminController {

  private CustomerService customerService;

  @Autowired
  public AdminController(CustomerService customerService) {
    this.customerService = customerService;
  }

  // list to get all customers
  // TODO add logging
  @GetMapping("/customers")
  public ResponseEntity<List<Customer>> getAllCustomers() {
    return ResponseEntity.ok(customerService.getAllCustomers());
  }

  // TODO add car
  @PostMapping("/addcar")
  public void addCar() {

  }

  // TODO delete car
  @DeleteMapping("/deletecar")
  public void deleteCar() {
    // TODO Use requestbody to get id
  }

  // TODO update car
  @PutMapping("/updatecar")
  public void updateCar() {
    // TODO Use requestbody to get id
  }

  // TODO addCustomer
  @PostMapping("/addcustomer")
  public void addCustomer() {

  }

  // TODO update customer
  @PutMapping("/updatecustomer")
  public void updateCustomer() {
   // TODO requestbody for ids
  }

  // TODO delete customer
  @DeleteMapping("/deletecustomer")
  public void deleteCustomer() {
    // TODO use requestbody for id
  }

  // List ALL cars (lista samtliga bilar)
  @GetMapping("/allcars")
  public void listAllCars() {

  }

  // TODO List all bookings
  @GetMapping("/orders")
  public void listAllBookings() {

  }

  // TODO delete booking
  @DeleteMapping("/deleteorder")
  public void deleteBooking() {
    // TODO use @RequestBody for id
  }

}
