package com.abcode.springrefresh.controller;


import com.abcode.springrefresh.dto.BeeDto;
import com.abcode.springrefresh.service.BeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bees")
public class BeeController {
    private final BeeService beeService;

    public BeeController(BeeService beeService) {
        this.beeService = beeService;
    }

    @PostMapping
    public ResponseEntity<BeeDto> createBee(@RequestBody BeeDto beeDto) {
        return ResponseEntity.ok(beeService.createBee(beeDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeeDto> getBeeById(@PathVariable Long id) {
        return ResponseEntity.ok(beeService.getBeeById(id));
    }

    @GetMapping
    public ResponseEntity<List<BeeDto>> getAllBees() {
        return ResponseEntity.ok(beeService.getAllBees());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeeDto> updateBee(@PathVariable Long id, @RequestBody BeeDto beeDto) {
        return ResponseEntity.ok(beeService.updateBee(id, beeDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBee(@PathVariable Long id) {
        beeService.deleteBee(id);
        return ResponseEntity.noContent().build();
    }
}