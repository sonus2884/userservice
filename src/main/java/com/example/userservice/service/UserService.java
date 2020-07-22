package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.model.User;

public interface UserService {

    User registerUser(UserDto userDto);

    User validateUser(String token);

}
