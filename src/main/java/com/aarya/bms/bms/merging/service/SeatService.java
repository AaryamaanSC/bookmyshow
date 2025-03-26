package com.aarya.bms.bms.merging.service;


import com.aarya.bms.bms.merging.dto.ShowSeatDto;
import com.aarya.bms.bms.merging.entity.ScreenSeats;
import com.aarya.bms.bms.merging.entity.ShowSeat;
import com.aarya.bms.bms.merging.enums.SeatState;
import com.aarya.bms.bms.merging.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;
    private final ModelMapper modelMapper;


    public ShowSeat createSeat(ShowSeatDto seatDto) {
        ShowSeat showSeat = modelMapper.map(seatDto,ShowSeat.class);
        return seatRepository.save(showSeat);
    }

    @Cacheable(value = "availableSeats", key = "#seatIds")
    public List<String> getAvailableSeatsOfShow(List<String> seatIds){
        List<String> availableSeatIds = new ArrayList<>();
        for(String seatId:seatIds){
            ShowSeat showSeat = seatRepository.findById(seatId)
                    .orElseThrow(()->new RuntimeException("Seat with id "+ seatId+" not found"));
            if(showSeat.getState()==SeatState.AVAILABLE){
                availableSeatIds.add(seatId);
            }
        }

        return availableSeatIds;
    }

    @CacheEvict(value = {"availableSeats", "seats"}, key = "#seatIds")
    public void reserveSeats(List<String> seatIds){
        List<ShowSeat> seats = seatRepository.findAllById(seatIds);
        seats.forEach(seat->seat.setState(SeatState.RESERVED));
        seatRepository.saveAll(seats);
    }

    @Cacheable(value = "seats", key = "#seatIds")
    public List<ShowSeat> getAllSeat(List<String> seatIds){
        return seatRepository.findAllById(seatIds);
    }

    @CacheEvict(value = {"availableSeats", "seats"}, key = "#seatIds")
    public void cancelSeats(List<String> seatIds){
        List<ShowSeat> seats = seatRepository.findAllById(seatIds);
        seats.forEach(seat->seat.setState(SeatState.AVAILABLE));
        seatRepository.saveAll(seats);
    }

    @CacheEvict(value = {"availableSeats", "seats"}, key = "#seatIds")
    public void confimSeats(List<String> seatIds){
        List<ShowSeat> seats = seatRepository.findAllById(seatIds);
        seats.forEach(seat->seat.setState(SeatState.BOOKED));
        seatRepository.saveAll(seats);
    }


}
