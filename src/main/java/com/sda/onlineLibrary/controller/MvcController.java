package com.sda.onlineLibrary.controller;

import com.sda.onlineLibrary.dto.BookDto;
import com.sda.onlineLibrary.entity.Book;
import com.sda.onlineLibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.Valid;

import java.io.IOException;
import java.util.List;

@Controller
public class MvcController {
    @Autowired
    private BookService bookService;
    @GetMapping("/home")
    public String getHome(){
        return "home";
    }

    @GetMapping("/addBook")
    public String addBook(Model model){
        model.addAttribute("bookDto", new BookDto());
        return "addBook";
    }

    @PostMapping("/addBook")
    public String addBookPost(@ModelAttribute (name= "bookDto") @Valid BookDto bookDto
            , BindingResult bindingResult, @RequestParam("bookPhoto") MultipartFile bookPhoto) throws IOException  {
        bookService.createBook(bookDto,bookPhoto );
        if(bindingResult.hasErrors()){
            return "addBook";
        }
        return "redirect:/addBook";
    }

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        List<BookDto> bookDtoList = bookService.getAllBookDtoList();
        model.addAttribute("books",  bookDtoList);
        return "books";
    }

    @PostMapping("/book/{bookId}")
    public String viewProductPost(@PathVariable(value = "bookId") String bookId){
        return "redirect:/book/" + bookId;
    }


}

