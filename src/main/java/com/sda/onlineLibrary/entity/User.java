package com.sda.onlineLibrary.entity;

import com.sda.onlineLibrary.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String personalEmail;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @ManyToMany( mappedBy = "users")
    private List<Book> books;
}
