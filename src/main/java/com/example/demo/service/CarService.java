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

  // TODO add logging
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
      if (car.isAvailable()) {
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
    Optional<Car> existingCar = carRepository.findById(carToBeUpdated.getId());

    StringBuilder updateMessage = new StringBuilder();



    if (existingCar.isPresent()) {
      updateMessage.append("\nAdmin updated Car with id: " + existingCar.get().getId() + "\n");
      if (carToBeUpdated.getDailyprice() != 0 || carToBeUpdated.getDailyprice() == existingCar.get().getDailyprice()) {
        existingCar.get().setDailyprice(carToBeUpdated.getDailyprice());
        updateMessage.append("Price per day changed to: " + existingCar.get().getDailyprice() + "\n");
      }
      if (carToBeUpdated.getManifacturer() != null && (!carToBeUpdated.getManifacturer().isEmpty() || !carToBeUpdated.getManifacturer().contains(existingCar.get().getManifacturer()))) {
        existingCar.get().setManifacturer(carToBeUpdated.getManifacturer());
        updateMessage.append("Manifacturer changed to: " + existingCar.get().getManifacturer() + "\n");
      }
      if (carToBeUpdated.getModel() != null && (!carToBeUpdated.getModel().isEmpty() || !carToBeUpdated.getModel().contains(existingCar.get().getModel()))) {
        existingCar.get().setModel(carToBeUpdated.getModel());
        updateMessage.append("Model changed to: " + existingCar.get().getModel() + "\n");
      }
      if (carToBeUpdated.getRegnr() != null && (!carToBeUpdated.getRegnr().isEmpty() || !carToBeUpdated.getRegnr().contains(existingCar.get().getRegnr()))) {
        existingCar.get().setRegnr(carToBeUpdated.getRegnr());
        updateMessage.append("Reg Nr changed to: " + existingCar.get().getRegnr() + "\n");
      }
      if (carToBeUpdated.isAvailable()) {
        if (!existingCar.get().isAvailable()) {
          existingCar.get().setAvailable(true);
          updateMessage.append("Changed availability to: true\n");
        }
      } else {
        if (existingCar.get().isAvailable()) {
          existingCar.get().setAvailable(false);
          updateMessage.append("Changed availability to: false\n");
        }
      }
      logger.info(updateMessage);
    } else {
      logger.info("\nWARN: Admin tried to update Car but nothing was updated on id: " + carToBeUpdated.getId() + "\n");
    }

    return existingCar.orElseThrow();
  }

  // DELETE
  // Delete car
  public String deleteCar(Car carToBeDeleted) {
    Optional<Car> theCar = carRepository.findById(carToBeDeleted.getId());

    if (theCar.isPresent()) {
      carRepository.delete(theCar.get());
      logger.info("\nAdmin deleted Car with id: " + carToBeDeleted.getId() + "\n");
      return "Deleted Car with id: " + carToBeDeleted.getId();
    } else {
      logger.warn("\nWARN: Admin tried to delete Car with ID that does not exist. ID used: " + carToBeDeleted.getId() + "\n");
      return "Car could not be found with id: " + carToBeDeleted.getId() + "\n";
    }
  }
}
