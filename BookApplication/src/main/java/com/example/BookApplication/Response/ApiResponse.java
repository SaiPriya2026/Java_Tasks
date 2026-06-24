package com.example.BookApplication.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private Integer  status;   // e.g., "success" or "error"
    private String message;  // human-readable message
    private T data;          // actual payload (Book, list, etc.)


}
