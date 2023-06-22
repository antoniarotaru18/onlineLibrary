package com.sda.onlineLibrary.controller;

import com.sda.onlineLibrary.dto.BookDto;
import com.sda.onlineLibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String addBookPost(@ModelAttribute (name= "bookDto") BookDto bookDto){
        bookService.addBook(bookDto);
        return "redirect:/addBook";
    }

}
