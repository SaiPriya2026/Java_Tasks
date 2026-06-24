package com.example.BookApplication.Service;

import com.example.BookApplication.Entity.Book;
import com.example.BookApplication.Repository.BookRepository;
import com.example.BookApplication.Exception.BookNotFoundException;
import com.example.BookApplication.Model.BookDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public BookDTO getBookById(Integer id) {
        Book book = bookRepository.findById(id)
                .filter(Book::isActive)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));

        BookDTO bookDto = new BookDTO();
        BeanUtils.copyProperties(book, bookDto);
        return bookDto;
    }

    public BookDTO getBookByName(String name) {
        Book book = bookRepository.findBookByTitleAndActiveTrue(name);

        if (book == null) {
            log.error("Book not found: {}", name);
            throw new BookNotFoundException("Book not found with name: " + name);
        }

        BookDTO bookDto = new BookDTO();
        BeanUtils.copyProperties(book, bookDto);
        return bookDto;
    }


    public BookDTO updateBook(BookDTO bookDto) {
        Book book = new Book();
        BeanUtils.copyProperties(bookDto, book);

        Book updatedBook = bookRepository.save(book);

        BookDTO updatedDto = new BookDTO();
        BeanUtils.copyProperties(updatedBook, updatedDto);
        return updatedDto;
    }


    public void deleteBook(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));

        log.info("Soft deleting book with id: {}", id);
        book.setActive(false);
        bookRepository.save(book);
    }


    public BookDTO addBook(BookDTO bookDto, MultipartFile image, MultipartFile file) {
        try {
            String baseDir = System.getProperty("user.dir") + File.separator + "uploads";

            File imageDir = new File(baseDir + File.separator + "images");
            File fileDir = new File(baseDir + File.separator + "files");

            if (!imageDir.exists()) imageDir.mkdirs();
            if (!fileDir.exists()) fileDir.mkdirs();

            String imagePath = imageDir.getAbsolutePath() + File.separator + image.getOriginalFilename();
            image.transferTo(new File(imagePath));

            String filePath = fileDir.getAbsolutePath() + File.separator + file.getOriginalFilename();
            file.transferTo(new File(filePath));

            Book book = new Book();
            BeanUtils.copyProperties(bookDto, book);
            book.setImagePath(imagePath);
            book.setFilePath(filePath);

            Book savedBook = bookRepository.save(book);

            BookDTO savedDto = new BookDTO();
            BeanUtils.copyProperties(savedBook, savedDto);
            return savedDto;

        } catch (IOException e) {
            log.error("File upload failed", e);
            throw new RuntimeException("File upload failed");
        }
    }


    public byte[] getFile(String filePath) {
        try {
            return Files.readAllBytes(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException("File not found");
        }
    }
}
