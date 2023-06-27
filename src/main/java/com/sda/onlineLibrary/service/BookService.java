package com.sda.onlineLibrary.service;

import com.sda.onlineLibrary.dto.BookDto;
import com.sda.onlineLibrary.entity.Book;
import com.sda.onlineLibrary.mapper.BookMapper;
import com.sda.onlineLibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookRepository bookRepository;

    public void createBook(BookDto bookDto, MultipartFile bookPhoto) {
        Book book = bookMapper.map(bookDto, bookPhoto);
        bookRepository.save(book);
    }
    public List<BookDto> getAllBookDtoList() {
        Iterable<Book> bookList = bookRepository.findAll();
        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book book : bookList) {
            BookDto bookDto= bookMapper.map(book);
            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }


}


