package com.example.userservice.controller;

import com.example.userservice.dto.ResponseDto;
import com.example.userservice.dto.UserDto;
import com.example.userservice.dto.UserResponseDto;
import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {

        this.userService = userService;
    }

    @PostMapping("/user/register")
    public ResponseDto<UserResponseDto> registerUser(@RequestBody UserDto userDto){

        User user = userService.registerUser(userDto);
        return new ResponseDto<>(
                HttpStatus.OK,
                new UserResponseDto(user.getId(),user.getFullName(),user.getEmail(),user.isActive())
        );
    }

    // /user/confirm?token=ajj23943j4563
    @GetMapping("/user/confirm")
    public ResponseDto<UserResponseDto> validateUser(@RequestParam String token){
        userService.validateUser(token);

        return null;
    }
}
