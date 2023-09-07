package com.sda.onlineLibrary.service;


import com.sda.onlineLibrary.dto.BookDto;
import com.sda.onlineLibrary.dto.ReviewDto;
import com.sda.onlineLibrary.entity.Book;
import com.sda.onlineLibrary.entity.Review;

import com.sda.onlineLibrary.mapper.ReviewMapper;
import com.sda.onlineLibrary.repository.BookRepository;
import com.sda.onlineLibrary.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReviewMapper reviewMapper;

    public void addReview(ReviewDto reviewDto) {
        Optional<Book> optionalBook = bookRepository.findById(Long.valueOf(reviewDto.getBookName()));
        if (optionalBook.isEmpty()) {
            return;
        }

        Review review = new Review();
        review.setBook(optionalBook.get());
        review.setRating(Integer.parseInt(reviewDto.getRating()));
        review.setReview(reviewDto.getReview());

        reviewRepository.save(review);
    }

    public List<ReviewDto> getAllReviews(String name) {
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        List<Review> reviews = reviewRepository.findByBookUsersPersonalEmail(name);
        for (Review review : reviews) {
            ReviewDto reviewDto = new ReviewDto();
            reviewDto.setBookName(review.getBook().getName());
            reviewDto.setRating(String.valueOf(review.getRating()));
            reviewDto.setReview(review.getReview());
            //reviewDto.setPhoto(Arrays.toString(review.getBook().getPhoto())); a fost prima data

            byte[] imageBytes = review.getBook().getPhoto();
            String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
            reviewDto.setPhotoBase64(imageBase64);

            reviewDtoList.add(reviewDto);
        }
        return reviewDtoList;
    }

    public Optional<ReviewDto> getOptionalReviewDtoById(String bookName) {
        Optional<Review> optionalReview = reviewRepository.findReviewByBookName(bookName);
        if (optionalReview.isEmpty()) {
            return Optional.empty();
        }
        Review review = optionalReview.get();
        ReviewDto reviewDto = reviewMapper.map(review);
        return Optional.of(reviewDto);
    }

}

