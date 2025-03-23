package com.aarya.bms.bms.merging.controller;

import com.aarya.bms.bms.merging.dto.ScreenSeatsDto;
import com.aarya.bms.bms.merging.dto.ShowDto;
import com.aarya.bms.bms.merging.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
@RequiredArgsConstructor
public class ShowController {
    private final ShowService showService;

    @GetMapping("/{id}")
    public ResponseEntity<ShowDto> getShow(@PathVariable String id){
        ShowDto showDto = showService.getShowById(id);
        return ResponseEntity.ok(showDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShowDto> updateScreenSeats(@PathVariable String id, @RequestBody ShowDto showDto) {
        ShowDto updatedShow = showService.updateShow(id, showDto);
        return ResponseEntity.ok(updatedShow);
    }


}
