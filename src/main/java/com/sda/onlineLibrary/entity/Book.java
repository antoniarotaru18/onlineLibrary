package com.sda.onlineLibrary.entity;

import com.sda.onlineLibrary.enums.Genre;
import com.sda.onlineLibrary.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String author;

    private String publisher;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    private Integer pages;

    private String description;

    private Status status;
    @Lob
    @Column(columnDefinition="LONGBLOB")
    private byte[] photo;


}
