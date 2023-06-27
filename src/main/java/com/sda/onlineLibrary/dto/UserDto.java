package com.sda.onlineLibrary.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotEmpty(message = "First name is mandatory!")
    @Size(min = 2, max = 20, message = "Name should not be too short, not too long")
    @Pattern(regexp = "^[A-Za-z -]+$", message = "First name contains illegal characters!")
    private String firstName;
    @NotEmpty(message = "Last name is mandatory!")
    @Size(min = 2, max = 20, message = "Name should not be too short, not too long")
    @Pattern(regexp = "^[A-Za-z -]+$", message = "Last name contains illegal characters!")
    private String lastName;
    @Email
    private String email;
    private String dateOfBirth;
    private String password;
    private String role;
}
