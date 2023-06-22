package com.sda.onlineLibrary.mapper;

import com.sda.onlineLibrary.dto.BookDto;
import com.sda.onlineLibrary.entity.Book;
import com.sda.onlineLibrary.enums.Genre;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Component
public class BookMapper {

    public Book map(BookDto bookDto){
        Book book = Book.builder()
                .name(bookDto.getName())
                .author(bookDto.getAuthor())
                .publisher(bookDto.getPublisher())
                .genre(Genre.valueOf(bookDto.getGenre()))
                .pages(Integer.valueOf(bookDto.getPages()))
                .description(bookDto.getDescription())
                .build();
        return book;
    }

//    public BookDto map(Book book){
//        BookDto bookDto = BookDto
//                .name(book.getName())
//                .author(book.getAuthor())
//                .publisher(book.getPublisher())
//                .genre(Genre.valueOf(bookDto.getGenre()))
//                .pages(book.getPages())
//                .description(book.getDescription())
//                .photo(Base64.encodeBase64String(book.getPhoto()))
//                .build();
//        return bookDto;
//    }
    private byte[] convertToBytes(MultipartFile multipartFile) {
        try {
            return multipartFile.getBytes();
        } catch (IOException e) {
            return new byte[0];
        }
    }
}
