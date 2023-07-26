package com.alinazim.bookexchange.mapper;

import com.alinazim.bookexchange.dao.entity.BookEntity;
import com.alinazim.bookexchange.dao.entity.UserEntity;
import com.alinazim.bookexchange.dao.repository.UserRepository;
import com.alinazim.bookexchange.model.request.BookRequest;
import com.alinazim.bookexchange.model.response.BookResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    default BookEntity buildBookEntity(BookRequest bookRequest, Optional<UserEntity> userEntityOptional) {
        if (bookRequest == null) {
            return null;
        }

        BookEntity bookEntity = new BookEntity();

        bookEntity.setTitle(bookRequest.getTitle());
        bookEntity.setAuthor(bookRequest.getAuthor());
        bookEntity.setGenre(bookRequest.getGenre());
        if (bookRequest.getUserId() != null) {
            if (userEntityOptional.isPresent()) {
                bookEntity.setUser(userEntityOptional.get());
            } else {
                throw new RuntimeException("User not found with id: " + bookRequest.getUserId());
            }
        }

        return bookEntity;
    }

    BookResponse buildBookResponse(BookEntity bookEntity);

    default List<BookEntity> buildBookEntities(List<BookRequest> bookRequests, UserRepository userRepository) {
        if (bookRequests == null) {
            return null;
        }

        List<BookEntity> bookEntities = new ArrayList<>();

        for (BookRequest bookRequest : bookRequests) {
            if (bookRequest != null) {
                Optional<UserEntity> userEntityOptional = userRepository.findById(bookRequest.getUserId());
                BookEntity bookEntity = buildBookEntity(bookRequest, userEntityOptional);
                bookEntities.add(bookEntity);
            }
        }

        return bookEntities;
    }

    List<BookResponse> buildBookResponses(List<BookEntity> entityList);
}

