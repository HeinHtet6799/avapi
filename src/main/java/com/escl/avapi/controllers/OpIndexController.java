package com.escl.avapi.controllers;

import com.escl.avapi.entities.OpIndex;
import com.escl.avapi.services.OpIndexService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/op-index")
@CrossOrigin(origins = "*")
public class OpIndexController {

    private OpIndexService opIndexService;

    public  OpIndexController(OpIndexService opIndexService) {
        this.opIndexService = opIndexService;
    }

    @GetMapping
    public ResponseEntity<List<OpIndex>> getAllOpIndexs() {
        return ResponseEntity.ok(opIndexService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OpIndex> getOpIndexById(@PathVariable Long id) {
        return ResponseEntity.ok(opIndexService.findById(id));
    }

    @PostMapping
    public ResponseEntity<OpIndex> createOpIndex(@RequestBody OpIndex opIndex) {
        return ResponseEntity.ok(opIndexService.save(opIndex));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OpIndex> updateOpIndex(@PathVariable Long id, @RequestBody OpIndex opIndex) {
        return ResponseEntity.ok(opIndexService.update(id,opIndex));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OpIndex> deleteOpIndex(@PathVariable Long id) {
        opIndexService.deleteOpIndex(id);
        return ResponseEntity.ok().build();
    }
}
