package com.sda.onlineLibrary.repository;

import com.sda.onlineLibrary.entity.Book;
import com.sda.onlineLibrary.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByPersonalEmail(String email);
}