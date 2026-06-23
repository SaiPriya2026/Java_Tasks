package com.example.BookApplication.Service;

import com.example.BookApplication.Entity.Book;
import com.example.BookApplication.Repository.BookRepository;
import com.example.BookApplication.Exception.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        log.info("Adding book: {}", book.getTitle());
        return bookRepository.save(book);
    }

    public Book getBookByName(String name) {
        log.info("Fetching book with name: {}", name);

        Book book = bookRepository.findBookByTitle(name);

        if (book == null) {
            log.error("Book not found: {}", name);
            throw new BookNotFoundException("Book not found with name: " + name);
        }
        return book;
    }

    public Book updateBook(Book book) {
        log.info("Updating book with id: {}", book.getId());
        return bookRepository.save(book);
    }

    public void deleteBook(Integer id) {
        log.warn("Deleting book with id: {}", id);

        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }

        bookRepository.deleteById(id);
    }
}
//package com.example.BookApplication.Service;
//
//import com.example.BookApplication.Entity.Book;
//import com.example.BookApplication.Repository.BookRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class BookService {
//    @Autowired
//    BookRepository bookRepository;
//    public Book addBook(Book book) {
//        return bookRepository.save(book);
//
//    }
//
//    public Book getBookByName(String name) {
//        return bookRepository.findBookByTitle(name);
//    }
//
//    public Book updateBook(Book book) {
//        return bookRepository .save(book);
//    }
//
//    public void deleteBook(Integer id) {
//        bookRepository.deleteById(id);
//    }
//}
