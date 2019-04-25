package com.mcb.creditfactory.controller;

import com.mcb.creditfactory.dto.AssessedValueDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.service.plane.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PlaneController {

    private PlaneService planeService;

    @Autowired
    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    @PostMapping("/planes/save")
    public HttpEntity<Long> save(@RequestBody Collateral object) {
        Long id = planeService.save(object).getId();
        return id != null ? ResponseEntity.ok(id) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/planes/info")
    public HttpEntity<Collateral> getInfo(@RequestBody Collateral object) {
        Collateral info = planeService.getInfo(object);
        return info != null ? ResponseEntity.ok(info) : ResponseEntity.notFound().build();
    }

    @GetMapping("/planes/{planeId}")
    public HttpEntity<Collateral> getInfo(@PathVariable("planeId") Long planeId) {
        Collateral plane = planeService.getCollateralById(planeId);
        return plane != null ? ResponseEntity.ok(plane) : ResponseEntity.notFound().build();
    }

    @PostMapping("/planes/{planeId}/values")
    public HttpEntity<Collateral> addValue(@PathVariable("planeId") Long planeId, @RequestBody AssessedValueDto value) {
        Collateral plane = planeService.addValue(planeId, value);
        return plane != null ? ResponseEntity.ok(plane) : ResponseEntity.badRequest().build();
    }
}
