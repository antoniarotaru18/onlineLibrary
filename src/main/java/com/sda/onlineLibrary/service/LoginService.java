package com.sda.onlineLibrary.service;

import com.sda.onlineLibrary.dto.LoginDto;
import com.sda.onlineLibrary.entity.User;
import com.sda.onlineLibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;
    public Boolean login(LoginDto loginDto){
        Optional<User> optionalUser = userRepository.findByPersonalEmail(loginDto.getEmail());
        if (optionalUser.isEmpty()){
            return false;
        }

        User user = optionalUser.get();
        return loginDto.getPassword().equals(user.getPassword());
    }
}
