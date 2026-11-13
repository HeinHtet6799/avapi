package com.escl.avapi.controllers;

import com.escl.avapi.entities.LensDescription;
import com.escl.avapi.services.LensDescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lens-description")
@CrossOrigin(origins = "*")
public class LensDescriptionController {

    private LensDescriptionService lensDescriptionService;

    public  LensDescriptionController(LensDescriptionService lensDescriptionService) {
        this.lensDescriptionService = lensDescriptionService;
    }

    @GetMapping
    public ResponseEntity<List<LensDescription>> getAllLensDescriptions() {
        return ResponseEntity.ok(lensDescriptionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LensDescription> getLensDescriptionById(@PathVariable Long id) {
        return ResponseEntity.ok(lensDescriptionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LensDescription> createLensDescription(@RequestBody LensDescription lensDescription) {
        return ResponseEntity.ok(lensDescriptionService.save(lensDescription));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LensDescription> updateLensDescription(@PathVariable Long id, @RequestBody LensDescription lensDescription) {
        return ResponseEntity.ok(lensDescriptionService.update(id,lensDescription));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LensDescription> deleteLensDescription(@PathVariable Long id) {
        lensDescriptionService.deleteLensDescription(id);
        return ResponseEntity.ok().build();
    }
}
