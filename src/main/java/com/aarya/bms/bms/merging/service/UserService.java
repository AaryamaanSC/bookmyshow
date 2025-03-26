package com.aarya.bms.bms.merging.service;

import com.aarya.bms.bms.merging.dto.UserDto;
import com.aarya.bms.bms.merging.entity.Booking;
import com.aarya.bms.bms.merging.entity.User;
import com.aarya.bms.bms.merging.exceptions.UserNotFoundException;
import com.aarya.bms.bms.merging.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Cacheable(value = "users", key = "#id")
    public UserDto getUser(String id) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        return modelMapper.map(user,UserDto.class);
    }

    @CachePut(value = "users", key = "#result.id")
    public UserDto createUser(UserDto userDTO) {
        User user = modelMapper.map(userDTO,User.class);
        user = userRepository.save(user);
        return modelMapper.map(user,UserDto.class);
    }

    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Cacheable(value = "userBookings", key = "#id")
    public List<Booking> getBookings(String id) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        List<String> bookingIds = user.getBookingIds();

        return null;
    }

    @CacheEvict(value = "userBookings", key = "#userId")
    public void removeUserBookings(String userId, String bookingId) {
    }
}
