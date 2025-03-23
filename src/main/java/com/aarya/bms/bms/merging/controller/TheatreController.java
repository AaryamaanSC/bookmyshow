package com.aarya.bms.bms.merging.controller;

import com.aarya.bms.bms.merging.dto.ScreenDto;
import com.aarya.bms.bms.merging.dto.TheaterDto;
import com.aarya.bms.bms.merging.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatres")
@RequiredArgsConstructor
public class TheatreController {

    private final TheaterService theaterService;


    @GetMapping("/{theaterId}")
    public ResponseEntity<TheaterDto> getTheater(@PathVariable String theaterId) {
        TheaterDto theatreDto = theaterService.getTheater(theaterId);
        return ResponseEntity.ok(theatreDto);
    }

    @PostMapping("/create")
    public ResponseEntity<TheaterDto> createTheater(@RequestBody TheaterDto theaterDTO) {
        return theaterService.CreateTheater(theaterDTO);
    }

    @DeleteMapping("/remove/{theaterId}")
    public ResponseEntity<String> deleteTheater(@PathVariable String theaterId) {
            theaterService.deleteTheater(theaterId);
            return ResponseEntity.ok("Theater deleted successfully");
    }


    @GetMapping("/{theaterId}/screens")
    public ResponseEntity<List<ScreenDto>> getAllScreensOfTheater(@PathVariable String theaterId){
        List<ScreenDto> screens = theaterService.getAllScreens(theaterId);
        return ResponseEntity.ok(screens);
    }

    @PostMapping("/{theaterId}/screen/add")
    public ResponseEntity<ScreenDto> addScreenToTheater(@PathVariable String theaterId, @RequestBody ScreenDto screenDto){
        return ResponseEntity.ok(theaterService.addScreenToTheatre(theaterId,screenDto));
    }

    @DeleteMapping("/{theaterId}/remove-screen/{screenId}")
    public ResponseEntity<String> removeScreenFromTheater(@PathVariable String theaterId, @PathVariable String screenId){
        theaterService.removeScreenFromTheatre(theaterId,screenId);
        return ResponseEntity.ok("Screen with id "+screenId+" removed successfully");
    }
}
