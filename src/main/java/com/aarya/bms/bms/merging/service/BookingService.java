package com.aarya.bms.bms.merging.service;

import com.aarya.bms.bms.merging.dto.BookingDto;
import com.aarya.bms.bms.merging.dto.ShowDto;
import com.aarya.bms.bms.merging.dto.UserDto;
import com.aarya.bms.bms.merging.entity.Booking;
import com.aarya.bms.bms.merging.entity.ShowSeat;
import com.aarya.bms.bms.merging.enums.BookingStatus;
import com.aarya.bms.bms.merging.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final ShowService showService;
    private final SeatService seatService;
    private final ModelMapper modelMapper;

    public BookingDto bookTicketForShow(BookingDto bookingDto) {

        Booking booking = modelMapper.map(bookingDto,Booking.class);
        UserDto user = userService.getUser(booking.getUserId());
        ShowDto show = showService.getShowById(booking.getShowId());
        List<String> availableSeat = showService.availableSeatsInShow(booking.getShowId());

        //check seat is valid or not
        List<String> seatsIdToBeBooked = booking.getSeatIdsToBeBooked();
        isValidSeats(seatsIdToBeBooked,availableSeat);

        //reserve Seat
        seatService.reserveSeats(seatsIdToBeBooked);

        //calc total price
        List<ShowSeat> seatsToBeBooked = seatService.getAllSeat(seatsIdToBeBooked);
        double totalPrice = calculateTotalSeatPrice(seatsToBeBooked);
        booking.setTotalPrice(totalPrice);
        booking.setBookingStatus(BookingStatus.ONGOING);

        //payment strategy pehle se hona chahiye iske paas
        boolean paymentSuccess = bookingDto.getPaymentStrategy().pay(totalPrice);
        if(!paymentSuccess){
            seatService.cancelSeats(seatsIdToBeBooked);
        }else{
            seatService.confimSeats(seatsIdToBeBooked);
        }

        //yha booking ka obj bana ke save kro db me
//        observer.NotificationObserver observer = new observer.EmailNotificationObserver(show.getId(), user.getEmail());
//        notificationObservableSubject.addObserver(observer);

        booking.setBookingStatus(BookingStatus.CONFIRMED);
        Booking savedBooking =  bookingRepository.save(booking);
        return modelMapper.map(savedBooking,BookingDto.class);
    }


    public void cancelBooking(String bookingId){
        if(!bookingRepository.existsById(bookingId)){
            throw new RuntimeException("Booking id not present");
        }
        Booking booking = findBookingById(bookingId);

        List<String> showSeatIds = booking.getSeatIdsToBeBooked();
        seatService.cancelSeats(showSeatIds);
        List<ShowSeat> seatsToBeBooked = seatService.getAllSeat(booking.getSeatIdsToBeBooked());

        double totalPrice = calculateTotalSeatPrice(seatsToBeBooked);


//        observer.NotificationObserver observer = new observer.EmailNotificationObserver(booking.getShowId(), userService.findUser(booking.getUserId()).getEmail());
//        notificationObservableSubject.removeObserver(observer);

        System.out.println("Return the money to user");


    }

    private Booking findBookingById(String id){
        return bookingRepository.findById(id).orElseThrow(()->new RuntimeException("booking with id "+id+ " not found"));
    }


    //HELPER

    private boolean isValidSeats(List<String> seats,List<String> availableSeat){
        for(String seatId: seats){
            if(!availableSeat.contains(seatId)){
                throw  new RuntimeException("Seats are already booked");
            }
        }
        return true;
    }


    private double calculateTotalSeatPrice(List<ShowSeat> seats){
        double price = 0.0;
        for(ShowSeat seat:seats){
            price += seat.getPrice();
        }
        return  price;
    }
}
