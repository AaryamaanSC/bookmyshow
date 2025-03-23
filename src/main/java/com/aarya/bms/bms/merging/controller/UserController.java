package com.aarya.bms.bms.merging.controller;

import com.aarya.bms.bms.merging.dto.UserDto;
import com.aarya.bms.bms.merging.entity.Booking;
import com.aarya.bms.bms.merging.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
            userService.deleteUser(id);
            return ResponseEntity.ok("user deleted successfully");
    }


    @GetMapping("/{id}/bookings")  //test
    public ResponseEntity<List<Booking>> getBookings(@PathVariable String id) {
            return ResponseEntity.ok(userService.getBookings(id));
    }

    @DeleteMapping("/{userId}/bookings/{bookingId}")
    public ResponseEntity<String> removeUserBookings(@PathVariable String userId, @PathVariable String bookingId) {
            userService.removeUserBookings(userId, bookingId);
            return ResponseEntity.ok("Booking removed successfully.");
    }
}
