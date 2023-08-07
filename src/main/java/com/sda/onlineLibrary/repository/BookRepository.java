package com.sda.onlineLibrary.repository;

import com.sda.onlineLibrary.entity.Book;
import com.sda.onlineLibrary.enums.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
//    @Query(value = "select b.* from book b " +
//            "join book_user bu on b.id=bu.book_id " +
//            "join user u on u.id= bu.user_id where u.personal_email = ?1 ", nativeQuery = true)
//    List<Book> findBookByUsername(String user);

    List<Book> findByUsersPersonalEmail(String personalEmail);

    List<Book> findByStatusAndUsersPersonalEmail(Status status, String personalEmail);
}
