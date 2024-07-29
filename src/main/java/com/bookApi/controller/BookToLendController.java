package com.bookApi.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookApi.entity.BookToLend;
import com.bookApi.repository.BookToLendRepository;
import com.bookApi.service.BookToLendService;

import ch.qos.logback.classic.Logger;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/booksToLend")
public class BookToLendController {
	
    @Autowired
    private BookToLendRepository BookToLendRepository;
    
    @Autowired
    private BookToLendService bookToLendService;
    
//******************************************************************************

    @GetMapping
    public List<BookToLend> getAllBooks() {
        return BookToLendRepository.findAll();
    }
    
    
//******************************************************************************

    @PostMapping("/save")
    public ResponseEntity<BookToLend> saveBookToLend(@RequestBody BookToLend bookToLend) {
        BookToLend savedBookToLend = bookToLendService.saveBookToLend(bookToLend);
        return ResponseEntity.status(201).body(savedBookToLend);
    }
    
//******************************************************************************
    
    @GetMapping("/{isbn}")
    public ResponseEntity<BookToLend> getBookToLendByIsbn(@PathVariable String isbn) {
        BookToLend bookToLend = bookToLendService.getBookToLendByIsbn(isbn);
        if (bookToLend != null) {
        	saveBookToLend(bookToLend);
            return ResponseEntity.ok(bookToLend);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
//******************************************************************************
    
//    @GetMapping("/fetch/{isbn}")
//    public ResponseEntity<BookToLend> findBookToLend(@PathVariable String isbn, User user) {
//        BookToLend bookToLend = bookToLendService.fetchBookDetailsFromGoogleBooks(isbn);
//        if (bookToLend != null) {
//            return ResponseEntity.ok(bookToLend);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

}
