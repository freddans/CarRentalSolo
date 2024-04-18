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

    @Autowired
    public BookingService(BookingRepository bookingRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.bookingRepository = bookingRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    // Create
    @Transactional
    public String bookCar(int carId, int customerId) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (optionalCar.isPresent() && optionalCustomer.isPresent()) {
            Car car = optionalCar.get();
            Customer customer = optionalCustomer.get();

            if (car.getAvailable()) { // Check if the car is available
                Booking booking = new Booking(car, customer);
                bookingRepository.save(booking);

                // Update car availability
                car.setAvailable(false);
                carRepository.save(car);

                logger.info("\nCustomer with ID: " + customerId + " booked Car with ID: " + carId + "\n");

                return "Car booked successfully!";
            } else {
                logger.info("\nERROR: Customer with ID: " + customerId + " tried to book Car with ID: " + carId + " that is not available\n");
                return "Car is not available for booking!";
            }
        } else {
            logger.info("\nERROR: Car or Customer not found\n");
            return "Car or customer not found!";
        }
    }

    public List<Booking> getFormerAndActiveBookings2(int customerId) {

        List<Booking> personalBookings = new ArrayList<>();

        for (Booking booking : bookingRepository.findAll()) {
            if (booking.getCustomer().getId() == customerId) {
                personalBookings.add(booking);
            }
        }

        logger.info("\nClient getting Former and Current bookings\n");
        return personalBookings;
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

    public Booking cancelBooking(Booking bookingToBeCanceled) {

        // get the booking
        Optional<Booking> booking = bookingRepository.findById(bookingToBeCanceled.getId());


        if (booking.isPresent()) {
            Booking theBooking = booking.get();

            // set due date:
            theBooking.setDueDate(new Date());

            // set car as available
            Car car = theBooking.getCar();
            car.setAvailable(true);

            // Get customer:
            Customer customer = theBooking.getCustomer();

            // Log it
            logger.info("\nCustomer with username: " + customer.getUsername() + " canceled their rental of car reg nr: " + car.getRegnr() + "\n");

            // save booking
            return bookingRepository.save(theBooking);
        } else {
            logger.warn("ERROR: Booking with id: " + bookingToBeCanceled.getId() + " does not exist");
            return booking.orElseThrow();
        }

    }
}
