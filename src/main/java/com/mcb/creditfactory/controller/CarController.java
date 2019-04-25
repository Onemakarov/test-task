package com.mcb.creditfactory.controller;

import com.mcb.creditfactory.dto.AssessedValueDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/cars/save")
    public HttpEntity<Collateral> save(@RequestBody Collateral object) {
        Collateral car = carService.save(object);
        return car != null ? ResponseEntity.ok(car) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/cars/info")
    public HttpEntity<Collateral> getInfo(@RequestBody Collateral object) {
        Collateral info = carService.getInfo(object);
        return info != null ? ResponseEntity.ok(info) : ResponseEntity.notFound().build();
    }

    @GetMapping("/cars/{carId}")
    public HttpEntity<Collateral> getInfo(@PathVariable("carId") Long carId) {
        Collateral car = carService.getCollateralById(carId);
        return car != null ? ResponseEntity.ok(car) : ResponseEntity.notFound().build();
    }

    @PostMapping("/cars/{carId}/values")
    public HttpEntity<Collateral> addValue(@PathVariable("carId") Long carId, @RequestBody AssessedValueDto value) {
        Collateral car = carService.addValue(carId, value);
        return car != null ? ResponseEntity.ok(car) : ResponseEntity.badRequest().build();
    }
}
