package com.sda.onlineLibrary.service;

import com.sda.onlineLibrary.dto.UserDto;
import com.sda.onlineLibrary.entity.User;
import com.sda.onlineLibrary.mapper.UserMapper;
import com.sda.onlineLibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public void createUser(UserDto userDto) {
        User user = userMapper.map(userDto);
        userRepository.save(user);
    }

    public List<UserDto> getAllUserDtoList() {
        List<UserDto> userDtoList = new ArrayList<>();

        Iterable<User> userIterable = userRepository.findAll();

        for (User user : userIterable) {
            UserDto userDto = userMapper.map(user);
            userDtoList.add(userDto);
        }
        return userDtoList;

    }
}
