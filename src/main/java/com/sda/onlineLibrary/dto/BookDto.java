package com.sda.onlineLibrary.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private String name;
    private String author;
    private String publisher;
    private String genre;
    private String pages;
    private String description;
    private String photo;
}
