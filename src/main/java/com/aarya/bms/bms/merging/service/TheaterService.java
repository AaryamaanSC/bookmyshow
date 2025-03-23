package com.aarya.bms.bms.merging.service;

import com.aarya.bms.bms.merging.dto.ScreenDto;
import com.aarya.bms.bms.merging.dto.TheaterDto;
import com.aarya.bms.bms.merging.entity.Theater;
import com.aarya.bms.bms.merging.exceptions.TheaterNotFoundException;
import com.aarya.bms.bms.merging.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheaterService {
    private final TheaterRepository theaterRepository;
    private final ScreenService screenService;
    private final ModelMapper modelMapper;


    public ResponseEntity<TheaterDto> CreateTheater(TheaterDto theaterDTO) {
        Theater theater = modelMapper.map(theaterDTO,Theater.class);
        theater = theaterRepository.save(theater);
        return ResponseEntity.ok(modelMapper.map(theater,TheaterDto.class));
    }

    public void deleteTheater(String theaterId) {
        Theater theater = theaterRepository.findById(theaterId).orElseThrow(()-> new TheaterNotFoundException(theaterId));

        screenService.removeAllScreens(theater.getScreenIdList());
        theaterRepository.deleteById(theaterId);
    }

    public TheaterDto getTheater(String theaterId) {
        Theater theater = getTheaterById(theaterId);
        return modelMapper.map(theater,TheaterDto.class);
    }

    public ScreenDto addScreenToTheatre(String theaterId,ScreenDto screenDto) {
        Theater theater = getTheaterById(theaterId);
        ScreenDto savedScreen = screenService.createScreen(screenDto);
        theater.getScreenIdList().add(savedScreen.getId());
        theaterRepository.save(theater);
        return savedScreen;
    }

    public void removeScreenFromTheatre(String theaterId, String screenId) {
        Theater theater = getTheaterById(theaterId);
        screenService.removeScreen(screenId);
        theater.getScreenIdList().remove(screenId);
        theaterRepository.save(theater);
    }

    public List<ScreenDto> getAllScreens(String theaterId) {
        Theater theater = getTheaterById(theaterId);
        return screenService.getAllScreens(theater.getScreenIdList());
    }

    //HELPER
    private Theater getTheaterById(String theaterId){
        return theaterRepository.findById(theaterId).orElseThrow(()-> new TheaterNotFoundException(theaterId));
    }
}
