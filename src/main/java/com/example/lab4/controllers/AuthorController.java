package com.example.lab4.controllers;

import com.example.lab4.models.Author;
import com.example.lab4.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAuthors() {
        return this.authorService.getAllAuthors();
    }

    @PostMapping
    public Author addAuthor(@RequestBody Author author) {
        return this.authorService.addAuthor(author);
    }

    @PutMapping("/{id}")
    public Author updateAuthorCountry(@PathVariable String id, @RequestBody Map<String, String> body) {
        return this.authorService.updateAuthorCountry(id, body.get("country"));
    }
}
