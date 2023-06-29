package com.sda.onlineLibrary.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class LoginDto {
    @Email(message = "Invalid email")
    @NotEmpty(message = "Email is missing")
    private String email;

    @Size(min = 3, message = "Password should be at least 3 characters long")
    private String password;


}
