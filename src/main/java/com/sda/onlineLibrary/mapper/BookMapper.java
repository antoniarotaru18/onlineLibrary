package com.sda.onlineLibrary.mapper;

import com.sda.onlineLibrary.dto.BookDto;
import com.sda.onlineLibrary.entity.Book;
import com.sda.onlineLibrary.enums.Genre;
import com.sda.onlineLibrary.enums.Status;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.sda.onlineLibrary.constant.Util.BASE64_PREFIX;

@Component
public class BookMapper {

    public Book map(BookDto bookDto, MultipartFile bookPhoto){
        Book book = Book.builder()
                .name(bookDto.getName())
                .author(bookDto.getAuthor())
                .publisher(bookDto.getPublisher())
                .genre(Genre.valueOf(bookDto.getGenre()))
                .pages(Integer.valueOf(bookDto.getPages()))
                .description(bookDto.getDescription())
                .status(Status.valueOf(bookDto.getStatus()))
                .photo(convertToBytes(bookPhoto))
                .build();
        return book;
    }

    private byte[] convertToBytes(MultipartFile multipartFile) {
        try {
            return multipartFile.getBytes();
        } catch (IOException e) {
            return new byte[0];
        }
    }


    public BookDto map(Book book){
        BookDto bookDto = BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .genre(String.valueOf(book.getGenre()))
                .pages(String.valueOf(book.getPages()))
                .status(String.valueOf(book.getStatus()))
                .description(book.getDescription())
                .photo(BASE64_PREFIX + Base64.encodeBase64String(book.getPhoto()))
                .build();
        return bookDto;
    }
}

