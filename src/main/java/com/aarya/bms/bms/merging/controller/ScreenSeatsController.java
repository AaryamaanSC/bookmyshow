package com.aarya.bms.bms.merging.controller;

import com.aarya.bms.bms.merging.dto.ScreenSeatsDto;
import com.aarya.bms.bms.merging.service.ScreenSeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/screen-seats")
public class ScreenSeatsController {
    private ScreenSeatService screenSeatsService;

    @PostMapping
    public ResponseEntity<ScreenSeatsDto> createScreenSeats(@RequestBody ScreenSeatsDto screenSeatsDto) {
        return ResponseEntity.ok(screenSeatsService.createScreenSeats(screenSeatsDto));
    }

    @GetMapping
    public ResponseEntity<List<ScreenSeatsDto>> getAllScreenSeats() {
        return ResponseEntity.ok(screenSeatsService.getAllScreenSeats());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScreenSeatsDto> getScreenSeatsById(@PathVariable String id) {
        ScreenSeatsDto screenSeatsDto = screenSeatsService.getScreenSeatById(id);
        return ResponseEntity.ok(screenSeatsDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScreenSeatsDto> updateScreenSeats(@PathVariable String id, @RequestBody ScreenSeatsDto screenSeatsDto) {
        ScreenSeatsDto updatedSeats = screenSeatsService.updateScreenSeats(id, screenSeatsDto);
        return ResponseEntity.ok(updatedSeats);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScreenSeatsDto> patchScreenSeats(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        ScreenSeatsDto updatedSeats = screenSeatsService.patchScreenSeats(id, updates);
        return ResponseEntity.ok(updatedSeats);
    }

    // Delete ScreenSeats
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScreenSeats(@PathVariable String id) {
        screenSeatsService.deleteScreenSeats(id);
        return ResponseEntity.noContent().build();
    }
}
