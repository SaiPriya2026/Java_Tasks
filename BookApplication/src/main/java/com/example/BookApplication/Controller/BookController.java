package com.example.BookApplication.Controller;

import com.example.BookApplication.Model.BookDTO;
import com.example.BookApplication.Response.ApiResponse;
import com.example.BookApplication.Service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<BookDTO>> addBook(
            @RequestPart("book") String bookJson,
            @RequestPart("image") MultipartFile image,
            @RequestPart("file") MultipartFile file) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        BookDTO bookDto = mapper.readValue(bookJson, BookDTO.class);

        BookDTO result = bookService.addBook(bookDto, image, file);

        ApiResponse<BookDTO> response = new ApiResponse<>();
        response.setStatus(201);
        response.setMessage("Book has been successfully added");
        response.setData(result);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/name/{bookName}")
    public ResponseEntity<ApiResponse<BookDTO>> getBookByName(@PathVariable("bookName") String name) {
        BookDTO bookDto = bookService.getBookByName(name);
        ApiResponse<BookDTO> response = new ApiResponse<>();
        response.setStatus(200);
        response.setMessage("Book has been successfully fetched");
        response.setData(bookDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/image/{id}")
    public ResponseEntity<ApiResponse<byte[]>> getBookImage(@PathVariable Integer id) {
        BookDTO bookDto = bookService.getBookById(id);
        byte[] image = bookService.getFile(bookDto.getImagePath());

        ApiResponse<byte[]> response = new ApiResponse<>();
        response.setStatus(200);
        response.setMessage("Image has been successfully found");
        response.setData(image);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/image/{id}/view")
    public ResponseEntity<byte[]> viewBookImage(@PathVariable Integer id) {
        BookDTO bookDto = bookService.getBookById(id);
        byte[] image = bookService.getFile(bookDto.getImagePath());

        return ResponseEntity.ok()
                .header("Content-Type", "image/png") // adjust if jpg
                .body(image);
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<ApiResponse<byte[]>> getBookFile(@PathVariable Integer id) {
        BookDTO bookDto = bookService.getBookById(id);
        byte[] fileData = bookService.getFile(bookDto.getFilePath());

        ApiResponse<byte[]> response = new ApiResponse<>();
        response.setStatus(200);
        response.setMessage("File has been successfully found");
        response.setData(fileData);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/file/{id}/view")
    public ResponseEntity<byte[]> viewBookFile(@PathVariable Integer id) {
        BookDTO bookDto = bookService.getBookById(id);
        byte[] fileData = bookService.getFile(bookDto.getFilePath());

        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf") // adjust if docx, etc.
                .header("Content-Disposition", "inline; filename=\"" + bookDto.getTitle() + ".pdf\"")
                .body(fileData);
    }



    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BookDTO>> updateBook(@PathVariable Integer id, @RequestBody BookDTO bookDto) {
        bookDto.setId(id);
        BookDTO updatedBook = bookService.updateBook(bookDto);

        ApiResponse<BookDTO> response = new ApiResponse<>();
        response.setStatus(200);
        response.setMessage("Book has been successfully updated");
        response.setData(updatedBook);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteBook(@PathVariable("id") Integer id) {
        bookService.deleteBook(id);
        ApiResponse<String> response = new ApiResponse<>();
        response.setStatus(200);
        response.setMessage("Book has been successfully deleted");
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
