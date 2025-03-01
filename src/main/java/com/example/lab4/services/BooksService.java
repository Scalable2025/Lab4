package com.example.lab4.services;

import com.example.lab4.models.Author;
import com.example.lab4.models.Book;
import com.example.lab4.repositories.AuthorRepository;
import com.example.lab4.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BooksService {
    BooksRepository booksRepository;
    AuthorRepository authorRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, AuthorRepository authorRepository) {
        this.booksRepository = booksRepository;
        this.authorRepository = authorRepository;
    }

    public List<Book> getAllBooks(){
        return this.booksRepository.findAll();
    }

    public Book addBook(Map<String, String> book){
        Optional<Author> author = this.authorRepository.findById(book.get("author_id"));
        if(author.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found");
        }
        return this.booksRepository.save(new Book(book.get("title"), author.get()));
    }
}
