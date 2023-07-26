package com.alinazim.bookexchange.service.concrete;

import com.alinazim.bookexchange.dao.entity.BookEntity;
import com.alinazim.bookexchange.dao.entity.UserEntity;
import com.alinazim.bookexchange.dao.repository.BookRepository;
import com.alinazim.bookexchange.dao.repository.UserRepository;
import com.alinazim.bookexchange.mapper.BookMapper;
import com.alinazim.bookexchange.model.request.BookRequest;
import com.alinazim.bookexchange.model.response.BookResponse;
import com.alinazim.bookexchange.service.abstraction.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceHandle implements BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    public void createBook(BookRequest bookRequest) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(bookRequest.getUserId());
        bookRepository.save(BookMapper.INSTANCE.buildBookEntity(bookRequest,userEntityOptional));
    }

    @Override
    public void updateBook(BookRequest bookRequest, Long id) {
        BookEntity bookEntity = fetchBookEntity(id);
        bookEntity.setTitle(bookRequest.getTitle());
        bookEntity.setAuthor(bookRequest.getAuthor());
        bookEntity.setGenre(bookRequest.getGenre());
        bookRequest.setUserId(bookRequest.getUserId());
        bookRepository.save(bookEntity);
    }

    @Override
    public BookResponse getBookById(Long id) {
        BookEntity bookEntity = fetchBookEntity(id);
        return BookMapper.INSTANCE.buildBookResponse(bookEntity);
    }

    @Override
    public List<BookResponse> getAllBooks() {
        return BookMapper.INSTANCE.buildBookResponses(bookRepository.findAll());
    }

    private BookEntity fetchBookEntity(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }
}
