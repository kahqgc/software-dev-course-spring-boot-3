package com.example.springBoot2.controllers;

import com.example.springBoot2.models.Book;
import com.example.springBoot2.Repositories.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("")
    public List<Book> getAlbums(){
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id){
        return bookRepository.findById(id).orElse(null);
    }

    @PostMapping("")
    public Book addBook (@RequestBody Book book){
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public Optional<Book> updateBook(@PathVariable int id, @RequestBody Book book){
        return bookRepository.findById(id).map(existingBook -> {
            existingBook.setName(book.getName());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setYear(book.getYear());
            existingBook.setPages(book.getPages());
            return bookRepository.save(existingBook);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id){
        bookRepository.deleteById(id);
    }
}
