package com.example.demo.controller;

import com.example.demo.entity.Car;
import com.example.demo.entity.Customer;
import com.example.demo.service.CarService;
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
  private CarService carService;

  @Autowired
  public AdminController(CustomerService customerService, CarService carService) {
    this.customerService = customerService;
    this.carService = carService;
  }



  // CREATE
  // add car
  @PostMapping("/addcar")
  public ResponseEntity<Car> addCar(@RequestBody Car newCar) {
    return ResponseEntity.ok(carService.addCar(newCar));
  }

  // add Customer
  @PostMapping("/addcustomer")
  public ResponseEntity<Customer> addCustomer(@RequestBody Customer newCustomer) {
    return ResponseEntity.ok(customerService.addCustomer(newCustomer));
  }

  // READ
  // list to get all customers
  @GetMapping("/customers")
  public ResponseEntity<List<Customer>> getAllCustomers() {
    return ResponseEntity.ok(customerService.getAllCustomers());
  }

  // List ALL cars (lista samtliga bilar)
  @GetMapping("/allcars")
  public ResponseEntity<List<Car>> listAllCars() {
    return ResponseEntity.ok(carService.getAllCars());
  }

  // UPDATE
  // update car
  @PutMapping("/updatecar")
  public ResponseEntity<Car> updateCar(@RequestBody Car carToBeUpdated) {
    return ResponseEntity.ok(carService.updateCar(carToBeUpdated));
  }

  // update customer
  @PutMapping("/updatecustomer")
  public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customerToBeUpdated) {
   return ResponseEntity.ok(customerService.updateCustomer(customerToBeUpdated));
  }

//  // TODO List all bookings
//  @GetMapping("/orders")
//  public void listAllBookings() {
//
//  }

  // DELETE
  // delete car
  @DeleteMapping("/deletecar")
  public ResponseEntity<String> deleteCar(@RequestBody Car carToBeDeleted) {
    // TODO Use requestbody to get id
    return ResponseEntity.ok(carService.deleteCar(carToBeDeleted));
  }

  // delete customer
  @DeleteMapping("/deletecustomer")
  public ResponseEntity<String> deleteCustomer(@RequestBody Customer customerToBeDeleted) {
    // TODO use requestbody for id
    return ResponseEntity.ok(customerService.deleteCustomer(customerToBeDeleted));
  }

//  // TODO delete booking
//  @DeleteMapping("/deleteorder")
//  public void deleteBooking() {
//    // TODO use @RequestBody for id
//  }

}
