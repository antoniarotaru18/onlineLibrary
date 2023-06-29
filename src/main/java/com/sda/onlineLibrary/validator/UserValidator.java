package com.sda.onlineLibrary.validator;

import com.sda.onlineLibrary.dto.UserDto;
import com.sda.onlineLibrary.entity.User;
import com.sda.onlineLibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Component
public class UserValidator {
    @Autowired
    private UserRepository userRepository;

    public void validate(UserDto userDto, BindingResult bindingResult) {

        validateUniqueEmail(userDto, bindingResult);

        validateDateOfBirth(userDto, bindingResult);
    }

    private void validateDateOfBirth(UserDto userDto, BindingResult bindingResult) {
        if(userDto.getDateOfBirth().isEmpty()){
            FieldError fieldError = new FieldError("userDto", "dateOfBirth", "Date of birth should be filled!");
            bindingResult.addError(fieldError);
        }
        try{
            LocalDate.parse(userDto.getDateOfBirth());
        } catch (DateTimeParseException exception){
            FieldError fieldError = new FieldError("userDto", "dateOfBirth", "Wrong format!");
            bindingResult.addError(fieldError);
        }

    }

    private void validateUniqueEmail(UserDto userDto, BindingResult bindingResult) {
        Optional<User> optionalUser = userRepository.findByPersonalEmail(userDto.getEmail());
        if (optionalUser.isPresent()) {
            FieldError fieldError = new FieldError("userDto", "email", "E-mail is already in use!");
            bindingResult.addError(fieldError);
        }
    }
}
