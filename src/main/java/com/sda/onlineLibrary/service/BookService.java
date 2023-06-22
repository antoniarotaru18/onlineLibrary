package com.sda.onlineLibrary.service;

import com.sda.onlineLibrary.dto.BookDto;
import com.sda.onlineLibrary.entity.Book;
import com.sda.onlineLibrary.mapper.BookMapper;
import com.sda.onlineLibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookRepository bookRepository;
    public void addBook(BookDto bookDto){
        Book book = bookMapper.map(bookDto);
        bookRepository.save(book);
    }
}
