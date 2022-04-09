package com.key.car.service;

import com.key.car.exception.ResourceNotFoundException;
import com.key.car.model.Car;
import com.key.car.repository.CarRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car createCar(Car car){
        return carRepository.save(car);
    }

    public Car findCarById(Long carId) throws ResourceNotFoundException{
      return  carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car not found for this id :: " + carId));
    }

    public Car updateCar(Long carId, Car carDetails) throws ResourceNotFoundException{
        Car car = findCarById(carId);
        BeanUtils.copyProperties(carDetails,car);
       car.setId(carId);
       return carRepository.save(car);
    }

    public void deleteCar(Long carId) throws ResourceNotFoundException{
        Car car = findCarById(carId);
        carRepository.delete(car);
    }
}
