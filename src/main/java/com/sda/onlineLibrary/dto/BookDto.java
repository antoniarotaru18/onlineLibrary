package com.sda.onlineLibrary.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {

    private String name;
    private String author;
    private String publisher;
    private String genre;
    private String pages;
    private String description;
    private String photo;
}
