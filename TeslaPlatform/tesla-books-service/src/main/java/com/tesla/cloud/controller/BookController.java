package com.tesla.cloud.controller;

import com.tesla.cloud.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public Object books(){
      return  bookService.getAllBooks();
    }

}
