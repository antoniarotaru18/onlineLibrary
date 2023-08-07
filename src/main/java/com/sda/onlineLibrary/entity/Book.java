package com.sda.onlineLibrary.entity;

import com.sda.onlineLibrary.enums.Genre;
import com.sda.onlineLibrary.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

    private Status status;
    @Lob
    @Column(columnDefinition="LONGBLOB")
    @ToString.Exclude
    private byte[] photo;

    @OneToOne(mappedBy = "book")
    private Review review;


    @ManyToMany
    @JoinTable(
            name="book_user",
            joinColumns = {@JoinColumn (name = "book_id")},
            inverseJoinColumns = {@JoinColumn (name = "user_id")}
    )
    @ToString.Exclude
    private List<User> users;
}
