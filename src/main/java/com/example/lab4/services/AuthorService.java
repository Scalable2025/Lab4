package com.example.lab4.services;

import com.example.lab4.models.Author;
import com.example.lab4.repositories.AuthorRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    AuthorRepository authorRepository;
    MongoClient mongoClient;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, MongoClient mongoClient) {
        this.authorRepository = authorRepository;
        this.mongoClient = mongoClient;
    }

    public List<Author> getAllAuthors(){
        return this.authorRepository.findAll();
    }

    public Author addAuthor(Author author) {
        return this.authorRepository.save(author);
    }

    public Author updateAuthorCountry(String id, String country){
        System.out.println("Updating author "+id+" country to "+country);
        MongoDatabase mongoDatabase = this.mongoClient.getDatabase("bookstore");
        MongoCollection<Document> authors = mongoDatabase.getCollection("authors");
        authors.updateOne(
                Filters.eq("_id", new ObjectId(id)),
                Updates.set("country", country)
        );
        Optional<Author> author = this.authorRepository.findById(id);
        if(author.isPresent()) {
            return author.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found");

    }
}
