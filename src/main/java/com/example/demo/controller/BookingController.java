package com.example.demo.controller;

import com.example.demo.entity.Booking;
import com.example.demo.entity.Car;
import com.example.demo.entity.Customer;
import com.example.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    // CREATE
    // CUSTOMER
    @PostMapping("/ordercar")
    public ResponseEntity<String> bookCar(@RequestParam("carId") int carId, @RequestParam("customerId") int customerId) {
        return ResponseEntity.ok(bookingService.bookCar(carId, customerId));
    }

    // ADMIN
    // TODO delete booking
    @DeleteMapping("/deleteorder")
    public ResponseEntity<String> deleteBooking(@RequestBody Booking bookingToBeDeleted) {
        return ResponseEntity.ok(bookingService.deleteBooking(bookingToBeDeleted));
    }


    // TODO List all bookings
    @GetMapping("/orders")
    public ResponseEntity<List<Booking>> listAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }


    // CUSTOMER
    // TODO View former and and active bookings
    @GetMapping("/myorders")
    public ResponseEntity<List<Booking>> viewFormerAndActiveBookings(@RequestParam("customerId") int customerId) {
        return ResponseEntity.ok(bookingService.getFormerAndActiveBookings2(customerId));
    }



    // TODO Cancel order
    @PutMapping("/cancelorder")
    public ResponseEntity<Booking> cancelBooking(@RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.cancelBooking(booking));
    }
}
