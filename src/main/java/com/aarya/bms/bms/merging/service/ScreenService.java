package com.aarya.bms.bms.merging.service;

import com.aarya.bms.bms.merging.dto.ScreenDto;
import com.aarya.bms.bms.merging.dto.ShowDto;
import com.aarya.bms.bms.merging.entity.Screen;
import com.aarya.bms.bms.merging.exceptions.ScreenNotFoundException;
import com.aarya.bms.bms.merging.repository.ScreenRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ScreenService {
    private final ScreenRepository screenRepository;
    private final ScreenSeatService screenSeatService;
    private final ShowService showService;
    private final ModelMapper modelMapper;


    public ScreenDto createScreen(ScreenDto screenDto) {
        Screen screen = modelMapper.map(screenDto,Screen.class);
        Screen savedScreen = screenRepository.save(screen);
        return modelMapper.map(savedScreen,ScreenDto.class);
    }

    public void removeScreen(String screenId) {
        Screen screen = getScreenById(screenId);
        showService.removeAllShows(screen.getShowIdList());
        screenSeatService.deleteScreenSeats(screen.getScreenSeatId());

        screenRepository.deleteById(screenId);
    }

    public List<ScreenDto> getAllScreens(List<String> screenIdList) {
        List<Screen> screens = screenRepository.findAllById(screenIdList);
        return screens
                .stream()
                .map(screen -> modelMapper.map(screen,ScreenDto.class))
                .toList();
    }

    public void removeAllScreens(List<String> screenIdList) {
        screenIdList.forEach(this::removeScreen);
    }

    public ShowDto addShowToScreen(String screenId, ShowDto showDto) {
        Screen screen = getScreenById(screenId);
        ShowDto savedShow = showService.createShow(showDto);
        screen.getShowIdList().add(savedShow.getId());
        screenRepository.save(screen);
        return showDto;
    }

    public void removeShowFromScreen(String screenId, String showId) {
        Screen screen = getScreenById(screenId);
        showService.removeShow(showId);
        screen.getShowIdList().remove(showId);
        screenRepository.save(screen);
    }

    //HELPER
    private Screen getScreenById(String screenId){
        return screenRepository.findById(screenId).orElseThrow(()->new ScreenNotFoundException(screenId));
    }
}
