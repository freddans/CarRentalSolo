package com.example.demo.service;

import com.example.demo.entity.Car;
import com.example.demo.repository.CarRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private Logger logger = Logger.getLogger(CarService.class);
    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    // CREATE
    // Add car
    public Car addCar(Car newCar) {
        logger.info("\nAdmin added new car with RegNr: " + newCar.getRegnr() + "\n");
        return carRepository.save(newCar);
    }

    // READ
    // Get list of all available cars
    public List<Car> getAllAvailableCars() {
        List<Car> availableCars = new ArrayList<>();

        for (Car car : carRepository.findAll()) {
            if (car.getAvailable()) {
                availableCars.add(car);
            }
        }
        logger.info("\nClient gets list of all available cars \n");
        return availableCars;
    }

    // Get all cars
    public List<Car> getAllCars() {
        logger.info("\nAdmin gets list of all cars \n");
        return carRepository.findAll();
    }

    // UPDATE
    // update car
    public Car updateCar(Car carToBeUpdated) {
        Optional<Car> optionalCar = carRepository.findById(carToBeUpdated.getId());

        StringBuilder updateMessage = new StringBuilder();

        if (optionalCar.isPresent()) {

            Car existingCar = optionalCar.get();

            updateMessage.append("\nAdmin updated Car with id: " + existingCar.getId() + "\n");
            if (carToBeUpdated.getDailyprice() != 0 || carToBeUpdated.getDailyprice() == existingCar.getDailyprice()) {
                existingCar.setDailyprice(carToBeUpdated.getDailyprice());
                updateMessage.append("Price per day changed to: " + existingCar.getDailyprice() + "\n");
            }
            if (carToBeUpdated.getManifacturer() != null && (!carToBeUpdated.getManifacturer().isEmpty() || !carToBeUpdated.getManifacturer().contains(existingCar.getManifacturer()))) {
                existingCar.setManifacturer(carToBeUpdated.getManifacturer());
                updateMessage.append("Manifacturer changed to: " + existingCar.getManifacturer() + "\n");
            }
            if (carToBeUpdated.getModel() != null && (!carToBeUpdated.getModel().isEmpty() || !carToBeUpdated.getModel().contains(existingCar.getModel()))) {
                existingCar.setModel(carToBeUpdated.getModel());
                updateMessage.append("Model changed to: " + existingCar.getModel() + "\n");
            }
            if (carToBeUpdated.getRegnr() != null && (!carToBeUpdated.getRegnr().isEmpty() || !carToBeUpdated.getRegnr().contains(existingCar.getRegnr()))) {
                existingCar.setRegnr(carToBeUpdated.getRegnr());
                updateMessage.append("Reg Nr changed to: " + existingCar.getRegnr() + "\n");
            }
            if (carToBeUpdated.getAvailable() != null) {
                if (!existingCar.getAvailable() && carToBeUpdated.getAvailable()) {
                    existingCar.setAvailable(true);
                    updateMessage.append("Changed availability to: true\n");
                } else if (existingCar.getAvailable() && !carToBeUpdated.getAvailable()) {
                    existingCar.setAvailable(false);
                    updateMessage.append("Changed availability to: false\n");
                }
            } else {



            }
            logger.info(updateMessage);
            carRepository.save(existingCar);
        } else {
            logger.info("\nWARN: Admin tried to update Car but nothing was updated on id: " + carToBeUpdated.getId() + "\n");
        }

        return optionalCar.orElseThrow();
    }

    // DELETE
    // Delete car
    public String deleteCar(Car carToBeDeleted) {
        Optional<Car> optionalCar = carRepository.findById(carToBeDeleted.getId());

        if (optionalCar.isPresent()) {
            Car theCar = optionalCar.get();

            carRepository.delete(theCar);
            logger.info("\nAdmin deleted Car with id: " + carToBeDeleted.getId() + "\n");
            return "Deleted Car with id: " + carToBeDeleted.getId();
        } else {
            logger.warn("\nWARN: Admin tried to delete Car with ID that does not exist. ID used: " + carToBeDeleted.getId() + "\n");
            return "Car could not be found with id: " + carToBeDeleted.getId() + "\n";
        }
    }
}
