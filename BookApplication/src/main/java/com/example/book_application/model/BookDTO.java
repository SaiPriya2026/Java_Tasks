package com.example.BookApplication.Model;

import lombok.Data;

@Data
public class BookDTO {

    private Integer id;
    private String title;
    private String author;
    private String genre;
    private String imagePath;
    private String filePath;
}


