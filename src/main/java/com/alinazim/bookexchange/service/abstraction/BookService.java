package com.alinazim.bookexchange.service.abstraction;

import com.alinazim.bookexchange.model.request.BookRequest;
import com.alinazim.bookexchange.model.response.BookResponse;

import java.util.List;

public interface BookService {
    void createBook(BookRequest bookRequest);

    void updateBook(BookRequest bookRequest, Long id);

    BookResponse getBookById(Long id);

    List<BookResponse> getAllBooks();
}