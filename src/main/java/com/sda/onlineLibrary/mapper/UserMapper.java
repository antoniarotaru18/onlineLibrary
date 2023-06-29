package com.sda.onlineLibrary.mapper;

import com.sda.onlineLibrary.dto.UserDto;
import com.sda.onlineLibrary.entity.User;
import com.sda.onlineLibrary.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class UserMapper {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User map(UserDto userDto) {

        String passwordEncoded = bCryptPasswordEncoder.encode(userDto.getPassword());
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .dateOfBirth(LocalDate.parse(userDto.getDateOfBirth()))
                .personalEmail(userDto.getEmail())
                .password(passwordEncoded)
                .role(Role.valueOf(userDto.getRole()))
                .build();
    }

    public UserDto map(User user) {


        return UserDto.builder()
                .email(user.getPersonalEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth() != null
                        ? user.getDateOfBirth().toString()
                        : "")
                .build();
    }


}
