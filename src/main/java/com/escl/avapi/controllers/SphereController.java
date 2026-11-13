package com.escl.avapi.controllers;

import com.escl.avapi.entities.Sphere;
import com.escl.avapi.services.SphereService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sphere")
@CrossOrigin(origins = "*")
public class SphereController {

    private SphereService sphereService;

    public  SphereController(SphereService sphereService) {
        this.sphereService = sphereService;
    }

    @GetMapping
    public ResponseEntity<List<Sphere>> getAllSpheres() {
        return ResponseEntity.ok(sphereService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sphere> getSphereById(@PathVariable Long id) {
        return ResponseEntity.ok(sphereService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Sphere> createSphere(@RequestBody Sphere sphere) {
        return ResponseEntity.ok(sphereService.save(sphere));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sphere> updateSphere(@PathVariable Long id, @RequestBody Sphere sphere) {
        return ResponseEntity.ok(sphereService.update(id,sphere));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Sphere> deleteSphere(@PathVariable Long id) {
        sphereService.deleteSphere(id);
        return ResponseEntity.ok().build();
    }
}
