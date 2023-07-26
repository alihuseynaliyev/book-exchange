package com.alinazim.bookexchange.model.request;

import lombok.Data;

@Data
public class BookRequest {
    private String title;
    private String author;
    private String genre;
    private Long userId;
}
