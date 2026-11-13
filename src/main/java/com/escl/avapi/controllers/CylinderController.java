package com.escl.avapi.controllers;

import com.escl.avapi.entities.Cylinder;
import com.escl.avapi.services.CylinderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cylinder")
@CrossOrigin(origins = "*")
public class CylinderController {

    private CylinderService cylinderService;

    public  CylinderController(CylinderService cylinderService) {
        this.cylinderService = cylinderService;
    }

    @GetMapping
    public ResponseEntity<List<Cylinder>> getAllCylinders() {
        return ResponseEntity.ok(cylinderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cylinder> getCylinderById(@PathVariable Long id) {
        return ResponseEntity.ok(cylinderService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Cylinder> createCylinder(@RequestBody Cylinder cylinder) {
        return ResponseEntity.ok(cylinderService.save(cylinder));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cylinder> updateCylinder(@PathVariable Long id, @RequestBody Cylinder cylinder) {
        return ResponseEntity.ok(cylinderService.update(id,cylinder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cylinder> deleteCylinder(@PathVariable Long id) {
        cylinderService.deleteCylinder(id);
        return ResponseEntity.ok().build();
    }
}
