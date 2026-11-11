package com.escl.avapi.controllers;

import com.escl.avapi.entities.StockType;
import com.escl.avapi.services.StockTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stockType")
@CrossOrigin(origins = "*")
public class StockTypeController {

    private final StockTypeService stockTypeService;

    public StockTypeController(StockTypeService stockTypeService) {
        this.stockTypeService = stockTypeService;
    }

    @GetMapping
    public ResponseEntity<List<StockType>> getAllStockTypes() {
        return ResponseEntity.ok(stockTypeService.findAll());
    }

    @PostMapping
    public ResponseEntity<StockType> createStockType(@RequestBody StockType stockType) {
        return ResponseEntity.ok(stockTypeService.save(stockType));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockType> getStockType(@PathVariable Long id) {
        return ResponseEntity.ok(stockTypeService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockType> updateStockType(@PathVariable Long id, @RequestBody StockType stockType) {
        return ResponseEntity.ok(stockTypeService.update(id,stockType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StockType> deleteStockType(@PathVariable Long id) {
        stockTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
