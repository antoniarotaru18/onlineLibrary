package com.sda.onlineLibrary.service;

import com.sda.onlineLibrary.entity.User;
import com.sda.onlineLibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserServiceDetailsImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("S-a apelat Load User By Username");
        Optional<User> optionalUser = userRepository.findByPersonalEmail(email);
        if (optionalUser.isEmpty()){
            System.out.println("nu s a gasit userul cu emailul: " + email);
            throw new UsernameNotFoundException(email);
        }

        User user = optionalUser.get();
        SimpleGrantedAuthority role = new SimpleGrantedAuthority(user.getRole().name());

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(role);

        return new org.springframework.security.core.userdetails.User(user.getPersonalEmail(), user.getPassword(), roles);




    }
}
