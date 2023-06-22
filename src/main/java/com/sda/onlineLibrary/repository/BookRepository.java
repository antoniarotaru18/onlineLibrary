package com.sda.onlineLibrary.repository;

import com.sda.onlineLibrary.entity.Book;
import org.springframework.data.repository.CrudRepository;
public interface BookRepository extends CrudRepository<Book, Long> {
        }
