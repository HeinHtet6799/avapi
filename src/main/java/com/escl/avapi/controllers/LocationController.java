package com.escl.avapi.controllers;

import com.escl.avapi.entities.Location;
import com.escl.avapi.services.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/location")
@CrossOrigin(origins = "*")//for testing
public class LocationController {

    private final LocationService  locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<Location>> findAll() {
        return ResponseEntity.ok(locationService.findAll());
    }

    @PostMapping
    public ResponseEntity<Location> save(@RequestBody Location location) {
        return ResponseEntity.ok(locationService.save(location));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(locationService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> update(@PathVariable("id") Long id, @RequestBody Location location) {
        return ResponseEntity.ok(locationService.update(id,location));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Location> delete(@PathVariable("id") Long id) {
         locationService.delete(id);
         return ResponseEntity.noContent().build();
    }
}
