package com.aarya.bms.bms.merging.service;

import com.aarya.bms.bms.merging.dto.ScreenSeatsDto;
import com.aarya.bms.bms.merging.entity.ScreenSeats;
import com.aarya.bms.bms.merging.exceptions.ScreenSeatsNotFoundException;
import com.aarya.bms.bms.merging.repository.ScreenSeatsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScreenSeatService {

    private final ScreenSeatsRepository screenSeatsRepository;
    private final ModelMapper modelMapper;

    public ScreenSeatsDto createScreenSeats(ScreenSeatsDto screenSeatsDto) {
        ScreenSeats screenSeats = modelMapper.map(screenSeatsDto, ScreenSeats.class);
        ScreenSeats savedScreenSeat = screenSeatsRepository.save(screenSeats);
        return modelMapper.map(savedScreenSeat, ScreenSeatsDto.class);
    }

    public List<ScreenSeatsDto> getAllScreenSeats() {
        return screenSeatsRepository
                .findAll()
                .stream()
                .map(screenSeat -> modelMapper.map(screenSeat, ScreenSeatsDto.class))
                .collect(Collectors.toList());
    }

    public ScreenSeatsDto getScreenSeatById(String id) {
        return screenSeatsRepository
                .findById(id)
                .map(screenSeats -> modelMapper.map(screenSeats, ScreenSeatsDto.class))
                .orElseThrow(()->new ScreenSeatsNotFoundException(id));
    }


    public ScreenSeatsDto updateScreenSeats(String id, ScreenSeatsDto screenSeatDto) {
        if (!screenSeatsRepository.existsById(id))  throw new ScreenSeatsNotFoundException(id);

        ScreenSeats screenSeats = modelMapper.map(screenSeatDto, ScreenSeats.class);
        screenSeats.setId(id);
        ScreenSeats updatedSeat = screenSeatsRepository.save(screenSeats);
        return modelMapper.map(updatedSeat, ScreenSeatsDto.class);
    }

    public ScreenSeatsDto patchScreenSeats(String id, Map<String, Object> updates) {
        ScreenSeats existingSeat = findById(id);

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(ScreenSeats.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingSeat, value);
            }
        });

        ScreenSeats updatedSeat = screenSeatsRepository.save(existingSeat);
        return modelMapper.map(updatedSeat, ScreenSeatsDto.class);
    }

    public void deleteScreenSeats(String id) {
        screenSeatsRepository.deleteById(id);
    }

    private ScreenSeats findById(String id){
        return screenSeatsRepository.findById(id).orElseThrow(()->new ScreenSeatsNotFoundException(id));
    }

}
