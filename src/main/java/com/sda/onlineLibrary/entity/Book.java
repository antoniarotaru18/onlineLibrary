package com.sda.onlineLibrary.entity;

import com.sda.onlineLibrary.enums.Genre;
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
    @Lob
    @Column(columnDefinition="LONGBLOB")
    private byte[] photo;


}
