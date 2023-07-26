package com.alinazim.bookexchange.model.response;

import lombok.Data;

@Data
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private String genre;
    private String status;
    private UserResponse user;
}

