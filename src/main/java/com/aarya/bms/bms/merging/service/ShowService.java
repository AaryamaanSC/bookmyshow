package com.aarya.bms.bms.merging.service;

import com.aarya.bms.bms.merging.dto.ScreenSeatsDto;
import com.aarya.bms.bms.merging.dto.ShowDto;
import com.aarya.bms.bms.merging.dto.ShowSeatDto;
import com.aarya.bms.bms.merging.entity.ScreenSeats;
import com.aarya.bms.bms.merging.entity.Show;
import com.aarya.bms.bms.merging.entity.ShowSeat;
import com.aarya.bms.bms.merging.enums.SeatState;
import com.aarya.bms.bms.merging.enums.SeatStatus;
import com.aarya.bms.bms.merging.enums.SeatType;
import com.aarya.bms.bms.merging.exceptions.ShowNotFoundException;
import com.aarya.bms.bms.merging.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.config.MongoAuditingBeanDefinitionParser;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ShowService {
    private final ShowRepository showRepository;
    private final ScreenSeatService screenSeatService;
    private final SeatService seatService;
    private final ModelMapper modelMapper;

    public ShowDto getShowById(String id){
        Show show = findShowById(id);
        return modelMapper.map(show,ShowDto.class);
    }


    public ShowDto createShow(ShowDto showDto) {
        Show show = modelMapper.map(showDto,Show.class);
        //generate seats
        List<String> seatIds = generateSeats(show);
        show.setShowSeatIdList(seatIds);

        Show savedShow = showRepository.save(show);
        return modelMapper.map(savedShow,ShowDto.class);
    }

    public void removeShow(String showId) {
        showRepository.deleteById(showId);
    }

    public void removeAllShows(List<String> showIdList) {
        showIdList.forEach(this::removeShow);
    }


    public ShowDto updateShow(String id, ShowDto showDto) {
        Show show = modelMapper.map(showDto,Show.class);
        show.setId(id);
        Show updatedShow = showRepository.save(show);
        return modelMapper.map(updatedShow,ShowDto.class);
    }

    private Show findShowById(String id){
        return showRepository.findById(id).orElseThrow(()->new ShowNotFoundException(id));
    }


    //HELPER
    public List<String> generateSeats(Show show){
        ScreenSeatsDto screenSeatsDto = screenSeatService.getScreenSeatById(show.getScreenSeatId());

        int row = screenSeatsDto.getTotalRows();
        int col = screenSeatsDto.getTotalCols();
        HashMap<SeatType,Integer> seatTypeAndCount = screenSeatsDto.getSeatTypeAndCount();
        HashMap<SeatType,Double> seatTypeAndPrice = show.getSeatTypeAndPrice();


        List<String> showSeatsId = new ArrayList<>();

        int r = 1;
        int c = 1;
        for(SeatType seatType:seatTypeAndCount.keySet()){
            int count = seatTypeAndCount.get(seatType);
            Double price = seatTypeAndPrice.get(seatType);
            for(int k = 0;k<count;k++){
                int rowOfSeat = c/col+1;
                int colOfSeat = c%col==0?col:c%col;
                c++;

                ShowSeatDto seatDto = new ShowSeatDto(r,c,show.getId(),SeatState.AVAILABLE,seatType,price);
                ShowSeat savedSeat = seatService.createSeat(seatDto);

                showSeatsId.add(savedSeat.getId());
            }
        }

        return showSeatsId;
    }

    public List<String> availableSeatsInShow(String showId){
        if(!showRepository.existsById(showId)){
            throw new RuntimeException("Show does not exist cannot get the seats");
        }
        Show show = findShowById(showId);
        List<String> showSeatIdList = show.getShowSeatIdList();

        return seatService.getAvailableSeatsOfShow(showSeatIdList);
    }

}
