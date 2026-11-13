package com.escl.avapi.controllers;

import com.escl.avapi.entities.LensType;
import com.escl.avapi.services.LensTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lens-type")
@CrossOrigin(origins = "*")
public class LensTypeController {

    private LensTypeService lensTypeService;

    public  LensTypeController(LensTypeService lensTypeService) {
        this.lensTypeService = lensTypeService;
    }

    @GetMapping
    public ResponseEntity<List<LensType>> getAllLensTypes() {
        return ResponseEntity.ok(lensTypeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LensType> getLensTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(lensTypeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LensType> createLensType(@RequestBody LensType lensType) {
        return ResponseEntity.ok(lensTypeService.save(lensType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LensType> updateLensType(@PathVariable Long id, @RequestBody LensType lensType) {
        return ResponseEntity.ok(lensTypeService.update(id,lensType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LensType> deleteLensType(@PathVariable Long id) {
        lensTypeService.deleteLensType(id);
        return ResponseEntity.ok().build();
    }
}
