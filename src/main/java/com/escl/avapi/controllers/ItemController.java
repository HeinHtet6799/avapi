package com.escl.avapi.controllers;

import com.escl.avapi.entities.Item;
import com.escl.avapi.services.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/item")
@CrossOrigin(origins = "*")
public class ItemController {

    private ItemService itemService;

    public  ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(itemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        return ResponseEntity.ok(itemService.save(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item item) {
        return ResponseEntity.ok(itemService.update(id,item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Item> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }
}
