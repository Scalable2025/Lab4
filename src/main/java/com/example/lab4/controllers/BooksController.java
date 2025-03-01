package com.example.lab4.controllers;

import com.example.lab4.models.Book;
import com.example.lab4.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BooksController {
    BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return booksService.getAllBooks();
    }

    @PostMapping
    public Book addBook(@RequestBody Map<String, String> book) {
        return booksService.addBook(book);
    }

}
