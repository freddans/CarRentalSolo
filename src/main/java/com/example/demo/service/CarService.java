package com.example.demo.service;

import com.example.demo.entity.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

  // TODO add logging
  private CarRepository carRepository;

  @Autowired
  public CarService(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  // Get list of all available cars
  public List<Car> getAllAvailableCars() {
    List availableCars = new ArrayList();

    for (Car car : carRepository.findAll()) {
      if (car.isAvailable()) {
        availableCars.add(car);
      }
    }
    return availableCars;
  }
}
