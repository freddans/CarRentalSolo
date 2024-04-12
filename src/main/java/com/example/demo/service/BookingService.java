package com.example.demo.service;

import com.example.demo.entity.Booking;
import com.example.demo.entity.Car;
import com.example.demo.entity.Customer;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.CustomerRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private Logger logger = Logger.getLogger(BookingService.class);
    private BookingRepository bookingRepository;
    private CarRepository carRepository;
    private CustomerRepository customerRepository;
    private List<Booking> bookings = new ArrayList<>();

    @Autowired
    public BookingService(BookingRepository bookingRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.bookingRepository = bookingRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    // Create
//    public String bookCar(Car car, Customer customer) {
//        Optional<Car> optionalCar = carRepository.findById(car.getId());
//        Optional<Customer> optionalCustomer = customerRepository.findById(customer.getId());
//
//        if (optionalCar.isPresent() && optionalCustomer.isPresent()) {
//            Car theCar = optionalCar.get();
//            Customer theCustomer = optionalCustomer.get();
//
//            if (theCar.getAvailable()) {
//
//                theCar.setAvailable(false);
//                Booking booking = new Booking(car, theCustomer);
//
//                bookings.add(booking);
//                logger.info("\nBooking car " + theCar.getManifacturer() + " " + theCar.getModel() + " for customer " + theCustomer.getName() + "\n");
//                return "Car booked";
//            } else {
//                logger.info("\nError: booking car. Car not available\n");
//                return "Error: Car not available";
//            }
//        }
//        logger.info("\nError: Customer tried to book a car where customer or car ID does not exist\n");
//        return "Error: Car or Customer do not exist with provided ID";
//    }

public List<Booking> getFormerAndActiveBookings() {
    logger.info("\nClient getting Former and Current bookings\n");
    return bookings;
}

// Delete booking
public String deleteBooking(Booking bookingToBeDeleted) {
    Optional<Booking> optionalBooking = bookingRepository.findById(bookingToBeDeleted.getId());

    if (optionalBooking.isPresent()) {
        Booking theBooking = optionalBooking.get();

        bookingRepository.delete(theBooking);
        logger.info("\nAdmin deleted Booking with ID: " + bookingToBeDeleted.getId() + "\n");
        return "Deleted Booking with id: " + bookingToBeDeleted.getId();
    } else {
        logger.warn("\nWARN: Admin tried to delete Booking with ID that does not exist. ID used: " + bookingToBeDeleted.getId() + "\n");
        return "Booking could not be found with ID: " + bookingToBeDeleted.getId() + "\n";
    }
}

public List<Booking> getAllBookings() {
    logger.info("\nAdmin getting all orders\n");
    return bookingRepository.findAll();
}


}
