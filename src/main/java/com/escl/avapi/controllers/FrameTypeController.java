package com.escl.avapi.controllers;

import com.escl.avapi.entities.FrameType;
import com.escl.avapi.services.FrameTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/frame-type")
@CrossOrigin(origins = "*")
public class FrameTypeController {

    private FrameTypeService frameTypeService;

    public  FrameTypeController(FrameTypeService frameTypeService) {
        this.frameTypeService = frameTypeService;
    }

    @GetMapping
    public ResponseEntity<List<FrameType>> getAllFrameTypes() {
        return ResponseEntity.ok(frameTypeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FrameType> getFrameTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(frameTypeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<FrameType> createFrameType(@RequestBody FrameType frameType) {
        return ResponseEntity.ok(frameTypeService.save(frameType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FrameType> updateFrameType(@PathVariable Long id, @RequestBody FrameType frameType) {
        return ResponseEntity.ok(frameTypeService.update(id,frameType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FrameType> deleteFrameType(@PathVariable Long id) {
        frameTypeService.deleteFrameType(id);
        return ResponseEntity.ok().build();
    }
}
