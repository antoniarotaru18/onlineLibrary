package com.sda.onlineLibrary.repository;

import com.sda.onlineLibrary.entity.Book;
import com.sda.onlineLibrary.entity.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository  extends CrudRepository<Review, Long> {
    List<Review> findByBookId(String bookId);

    List<Review> findByBookUsersPersonalEmail(String personalEmail);

    Optional<Review> findReviewByBookName(String bookName);

}
