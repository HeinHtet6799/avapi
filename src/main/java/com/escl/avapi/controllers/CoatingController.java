package com.escl.avapi.controllers;

import com.escl.avapi.entities.Coating;
import com.escl.avapi.services.CoatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coating")
@CrossOrigin(origins = "*")
public class CoatingController {

    private CoatingService coatingService;

    public  CoatingController(CoatingService coatingService) {
        this.coatingService = coatingService;
    }

    @GetMapping
    public ResponseEntity<List<Coating>> getAllCoatings() {
        return ResponseEntity.ok(coatingService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coating> getCoatingById(@PathVariable Long id) {
        return ResponseEntity.ok(coatingService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Coating> createCoating(@RequestBody Coating coating) {
        return ResponseEntity.ok(coatingService.save(coating));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coating> updateCoating(@PathVariable Long id, @RequestBody Coating coating) {
        return ResponseEntity.ok(coatingService.update(id,coating));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Coating> deleteCoating(@PathVariable Long id) {
        coatingService.deleteCoating(id);
        return ResponseEntity.ok().build();
    }
}
