package com.key.car.controller;

import com.key.car.config.SwaggerConfiguration;
import com.key.car.exception.GenericError;
import com.key.car.exception.ResourceNotFoundException;
import com.key.car.model.Car;
import com.key.car.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api" )
@Validated
@Api(tags = {SwaggerConfiguration.CAR_TAG})
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping(value = "/cars", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Get all cars",
            notes = "Returns all cars",
            response = Car.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success..Fetched Cars"),
            @ApiResponse(code = 500, message = "Internal Error", response = GenericError.class),
    })
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/cars/{id}")
    @ApiOperation(
            value = "Get car by id",
            notes = "Returns car for id specified.",
            response = Car.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success..Fetched Car"),
            @ApiResponse(code = 404, message = "Car not found")})
    public ResponseEntity<Car> getCar(@PathVariable(value = "id") Long carId) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(carService.findCarById(carId));
    }

    @PostMapping(value = "/cars", produces =MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Create new car",
            notes = "Creates new car. Returns created car with id.",
            response = Car.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success..Car resource created"),
            @ApiResponse(code = 500, message = "Internal Error", response = GenericError.class)})
    public ResponseEntity<Car> createCar(@Valid @RequestBody Car car) {
        return ResponseEntity.ok().body(carService.createCar(car));

    }

    @PutMapping(value="/cars/{id}", produces =MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "update existing car",
            notes = "Creates existing car. Returns updated car with the path id.",
            response = Car.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success.. Car resource updated with given Id"),
            @ApiResponse(code = 404, message = "Car not found"),
            @ApiResponse(code = 500, message = "Internal Error", response = GenericError.class)})
    public ResponseEntity<Car> updateCar(@PathVariable(value = "id") Long carId, @RequestBody Car car) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(carService.updateCar(carId, car));
    }

    @DeleteMapping(value ="/cars/{id}", produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(
            value = "deleting existing car with path id",
            notes = "Creates existing car. Returns updated car with the path id.",
            response = HttpStatus.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Car deleted for the specified Id",response = Void.class),
            @ApiResponse(code = 404, message = "Car not found"),
            @ApiResponse(code = 500, message = "Internal Error", response = GenericError.class)})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteCar(@PathVariable(value = "id") Long carId) throws ResourceNotFoundException {
        carService.deleteCar(carId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
