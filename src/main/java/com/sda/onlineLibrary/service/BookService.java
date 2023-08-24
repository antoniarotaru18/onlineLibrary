package com.sda.onlineLibrary.service;

import com.sda.onlineLibrary.dto.BookDto;
import com.sda.onlineLibrary.dto.ReviewDto;
import com.sda.onlineLibrary.entity.Book;
import com.sda.onlineLibrary.entity.Review;
import com.sda.onlineLibrary.entity.User;
import com.sda.onlineLibrary.enums.Status;
import com.sda.onlineLibrary.mapper.BookMapper;
import com.sda.onlineLibrary.repository.BookRepository;
import com.sda.onlineLibrary.repository.ReviewRepository;
import com.sda.onlineLibrary.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;


    public void createBook(BookDto bookDto, MultipartFile bookPhoto, String email) {
        Optional<User> optionalUser = userRepository.findByPersonalEmail(email);
        if (optionalUser.isEmpty()) {
            return;
        }
        Book book = bookMapper.map(bookDto, bookPhoto, optionalUser.get());
        bookRepository.save(book);
    }

    public List<BookDto> getAllBookDtoListByUsername(Authentication authentication) {
        String user = authentication.getName();
        Iterable<Book> bookList = bookRepository.findByUsersPersonalEmail(user);
        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book book : bookList) {
            BookDto bookDto = bookMapper.map(book);
            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }

    public List<BookDto> getBookDtoListByOwnerEmailAndStatus(String email, Status status) {

        List<BookDto> bookDtoList = new ArrayList<>();
        List<Book> books = bookRepository.findByStatusAndUsersPersonalEmail(status, email);
        for (Book book : books) {
            BookDto bookDto = new BookDto();
            bookDto.setId(book.getId());
            bookDto.setName(book.getName());
            bookDto.setAuthor(book.getAuthor());
            bookDto.setPublisher(book.getPublisher());
            bookDto.setGenre(String.valueOf(book.getGenre()));
            bookDto.setPages(String.valueOf(book.getPages()));
            bookDto.setStatus(String.valueOf(book.getStatus()));
            bookDto.setPhoto(String.valueOf(book.getPhoto()));

            bookDtoList.add(bookDto);
        }
        List<BookDto> booksWithoutReview = new ArrayList<>();
        for (BookDto bookDto : bookDtoList) {
            List<Review> reviews = reviewRepository.findByBookId(String.valueOf(bookDto.getId()));
            if (reviews.isEmpty()) {
                booksWithoutReview.add(bookDto);
            }
        }
        return booksWithoutReview;
    }

    public Optional<BookDto> getOptionalBookDtoById(String bookId) {
        Optional<Book> optionalBook = bookRepository.findById(Long.valueOf(bookId));
        if (optionalBook.isEmpty()) {
            return Optional.empty();
        }
        Book product = optionalBook.get();
        BookDto productDto = bookMapper.map(product);
        return Optional.of(productDto);
    }

    public void updateBookStatus(String bookId, Status newStatus) {
        Optional<Book> optionalBook = bookRepository.findById(Long.valueOf(bookId));
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            Status currentStatus = book.getStatus();

            if (newStatus != currentStatus) {
                book.setStatus(newStatus);
                bookRepository.save(book);
            }
        }
    }

}

//    public Optional<BookDto> canAddReview(String bookId) {
//        Optional<BookDto> optionalBookDto = getOptionalBookDtoById(bookId);
//        if (optionalBookDto.isPresent()) {
//            BookDto bookDto = optionalBookDto.get();
//            if (bookDto.getStatus().equals("read")) {
//                return optionalBookDto;
//            }
//        }
//        return Optional.empty();
//    }