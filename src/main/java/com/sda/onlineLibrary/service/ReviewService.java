package com.sda.onlineLibrary.service;

import com.sda.onlineLibrary.dto.ReviewDto;
import com.sda.onlineLibrary.entity.Book;
import com.sda.onlineLibrary.entity.Review;
import com.sda.onlineLibrary.repository.BookRepository;
import com.sda.onlineLibrary.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookRepository bookRepository;

    public void addReview(ReviewDto reviewDto) {
        Optional<Book> optionalBook = bookRepository.findById(Long.valueOf(reviewDto.getBookId()));
        if(optionalBook.isEmpty()){
            return;
        }

        Review review = new Review();
        review.setBook(optionalBook.get());
        review.setRating(Integer.parseInt(reviewDto.getRating()));
        review.setReview(reviewDto.getReview());

        reviewRepository.save(review);
    }



}
