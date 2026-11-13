package com.escl.avapi.controllers;

import com.escl.avapi.entities.Base;
import com.escl.avapi.services.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/base")
@CrossOrigin(origins = "*")
public class BaseController {


    private BaseService baseService;

    public  BaseController(BaseService baseService) {
        this.baseService = baseService;
    }

    @GetMapping
    public ResponseEntity<List<Base>> getAllBases() {
        return ResponseEntity.ok(baseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Base> getBaseById(@PathVariable Long id) {
        return ResponseEntity.ok(baseService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Base> createBase(@RequestBody Base base) {
        return ResponseEntity.ok(baseService.save(base));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Base> updateBase(@PathVariable Long id, @RequestBody Base base) {
        return ResponseEntity.ok(baseService.update(id,base));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Base> deleteBase(@PathVariable Long id) {
        baseService.deleteBase(id);
        return ResponseEntity.ok().build();
    }
}
