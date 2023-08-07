package com.sda.onlineLibrary.controller;

import com.sda.onlineLibrary.dto.BookDto;
import com.sda.onlineLibrary.dto.LoginDto;
import com.sda.onlineLibrary.dto.ReviewDto;
import com.sda.onlineLibrary.dto.UserDto;
import com.sda.onlineLibrary.enums.Status;
import com.sda.onlineLibrary.service.BookService;
import com.sda.onlineLibrary.service.LoginService;
import com.sda.onlineLibrary.service.ReviewService;
import com.sda.onlineLibrary.service.UserService;
import com.sda.onlineLibrary.validator.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.Valid;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class MvcController {
    @Autowired
    private BookService bookService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/home")
    public String getHome() {
        return "home";
    }

    @GetMapping("/addBook")
    public String addBook(Model model) {
        model.addAttribute("bookDto", new BookDto());
        return "addBook";
    }

    @PostMapping("/addBook")
    public String addBookPost(@ModelAttribute(name = "bookDto") @Valid BookDto bookDto
            , BindingResult bindingResult, @RequestParam("bookPhoto") MultipartFile bookPhoto, Authentication authentication) throws IOException {

        bookService.createBook(bookDto, bookPhoto, authentication.getName());
        if (bindingResult.hasErrors()) {
            return "addBook";
        }
        return "redirect:/addBook";
    }

    @GetMapping("/books")
    public String getAllBooks(Model model, Authentication authentication) {
        // ded tirmis la serviciu authentication.get ca fiind user logat
        List<BookDto> bookDtoList = bookService.getAllBookDtoListByUsername(authentication);
        model.addAttribute("books", bookDtoList);
        return "viewBooks";
    }

    @PostMapping("/book/{bookId}")
    public String viewProductPost(@PathVariable(value = "bookId") String bookId) {
        return "redirect:/book/" + bookId;
    }

    @PostMapping("/book/{bookId}/updateStatus")
    public String updateBookStatus(@PathVariable(value = "bookId") String bookId, @RequestParam("newStatus") String newStatus) {
        Status status = Status.valueOf(newStatus);
        bookService.updateBookStatus(bookId, status);
        return "redirect:/book/" + bookId;
    }
    @GetMapping("/registration")
    public String registrationGet(Model model) {
        System.out.println("S-a apelat registration GET");
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationPost(@ModelAttribute(name = "userDto") @Valid UserDto userDto,
                                   BindingResult bindingResult) {
        userValidator.validate(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.createUser(userDto);

        return "redirect:/registration";
    }

    @GetMapping("/login")
    public String loginGet(Model model) {
        LoginDto loginDto = new LoginDto();
        model.addAttribute("loginDto", loginDto);
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute(name = "loginDto") @Valid LoginDto loginDto, BindingResult bindingResult, Model model) {
        System.out.println(loginDto);
        if (bindingResult.hasErrors()) {
            return "login";
        }
        Boolean loginSuccessfull = loginService.login(loginDto);
        if (loginSuccessfull) {
            model.addAttribute("loginMessage", "Login was successfull!!!");
        } else {
            model.addAttribute("loginMessage", "Login failed!!!");
        }
        return "login";

    }

    @GetMapping("/addReview")
    public String addReviewGet(Model model, Authentication authentication) {
        String userEmail = authentication.getName();
        model.addAttribute("reviewDto", new ReviewDto());

        List<BookDto> bookDtoList = bookService.getBookDtoListByOwnerEmailAndStatus(userEmail, Status.READ);
        model.addAttribute("bookDtoList", bookDtoList);
        return "addReview";
    }

    @PostMapping("/addReview")
    public String addReviewPost(ReviewDto reviewDto, Model model) {
        reviewService.addReview(reviewDto);

        return "redirect:/addReview";
    }


    @GetMapping("/reviews")
    public String getAllReviews() {
        return "reviews";
    }

    @PostMapping("/reviews")
    public String addReviewsPost() {
        return "redirect:/reviews";
    }

    @ModelAttribute("requestUrl")
    public String requestUrl(HttpServletRequest request) {
        return request.getRequestURI();
    }

    @GetMapping("/book/{bookId}")
    public String viewBookGet(@PathVariable("bookId") String bookId, Model model) {
        Optional<BookDto> optionalBookDto = bookService.getOptionalBookDtoById(bookId);
        if (optionalBookDto.isEmpty()) {
            return "error";
        }
        BookDto bookDto = optionalBookDto.get();
        model.addAttribute("bookDto", bookDto);

        return "viewBookDetails";
    }


}

