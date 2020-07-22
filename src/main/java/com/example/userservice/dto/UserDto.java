package com.example.userservice.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class UserDto {

    @NonNull
    @Size(min = 5)
    private String fullName;

    @NonNull
    @Size(min = 1)
    private String email;

    // TODO: Implement Custom Validator
    @NonNull
    @Size(min = 6)
    private String password;

}
