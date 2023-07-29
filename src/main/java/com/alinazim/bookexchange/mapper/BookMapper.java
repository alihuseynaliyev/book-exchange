package com.alinazim.bookexchange.mapper;

import com.alinazim.bookexchange.dao.entity.BookEntity;
import com.alinazim.bookexchange.dao.entity.UserEntity;
import com.alinazim.bookexchange.model.request.BookRequest;
import com.alinazim.bookexchange.model.response.BookResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    default BookEntity buildBookEntity(BookRequest bookRequest, UserEntity userEntity) {
        if (bookRequest == null) {
            return null;
        }

        var bookEntity = new BookEntity();

        bookEntity.setTitle(bookRequest.getTitle());
        bookEntity.setAuthor(bookRequest.getAuthor());
        bookEntity.setGenre(bookRequest.getGenre());
        if (bookRequest.getUserId() != null) {
            bookEntity.setUser(userEntity);
        }

        return bookEntity;
    }

    BookResponse buildBookResponse(BookEntity bookEntity);

    List<BookResponse> buildBookResponses(List<BookEntity> entityList);
}

