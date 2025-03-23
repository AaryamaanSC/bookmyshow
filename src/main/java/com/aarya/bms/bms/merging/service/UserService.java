package com.aarya.bms.bms.merging.service;

import com.aarya.bms.bms.merging.dto.UserDto;
import com.aarya.bms.bms.merging.entity.Booking;
import com.aarya.bms.bms.merging.entity.User;
import com.aarya.bms.bms.merging.exceptions.UserNotFoundException;
import com.aarya.bms.bms.merging.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserDto getUser(String id) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        return modelMapper.map(user,UserDto.class);
    }

    public UserDto createUser(UserDto userDTO) {
        User user = modelMapper.map(userDTO,User.class);
        user = userRepository.save(user);
        return modelMapper.map(user,UserDto.class);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public List<Booking> getBookings(String id) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        List<String> bookingIds = user.getBookingIds();

        return null;
    }

    public void removeUserBookings(String userId, String bookingId) {
    }
}
