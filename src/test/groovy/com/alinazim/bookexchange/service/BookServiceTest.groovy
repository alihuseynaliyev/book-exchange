package com.alinazim.bookexchange.service

import com.alinazim.bookexchange.dao.entity.BookEntity
import com.alinazim.bookexchange.dao.entity.UserEntity
import com.alinazim.bookexchange.dao.repository.BookRepository
import com.alinazim.bookexchange.dao.repository.UserRepository
import com.alinazim.bookexchange.mapper.BookMapper
import com.alinazim.bookexchange.model.request.BookRequest
import com.alinazim.bookexchange.service.abstraction.BookService
import com.alinazim.bookexchange.service.concrete.BookServiceHandle
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class BookServiceTest extends Specification {

    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private BookService bookService
    private BookRepository bookRepository
    private UserRepository userRepository

    def setup() {
        bookRepository = Mock()
        userRepository = Mock()
        bookService = new BookServiceHandle(bookRepository, userRepository)
    }

    def "Test createBook() success"() {
        given:
        def bookRequest = random.nextObject(BookRequest, "userId")
        bookRequest.userId = 1
        def userEntity = random.nextObject(UserEntity, "id")
        userEntity.id = 1
        def entity = BookMapper.INSTANCE.buildBookEntity(bookRequest, userEntity)

        when:
        bookService.createBook(bookRequest)

        then:
        1 * userRepository.findById(bookRequest.userId) >> Optional.of(userEntity)
        1 * bookRepository.save(entity)
    }

    def "Test createBook() error occurred when user not found"() {
        given:
        def id = random.nextLong()
        def bookRequest = random.nextObject(BookRequest, "userId")
        bookRequest.userId = id
        def userEntity = random.nextObject(UserEntity, "id")
        userEntity.id = id

        when:
        bookService.createBook(bookRequest)

        then:
        1 * userRepository.findById(bookRequest.userId) >> Optional.empty()
        0 * bookRepository.save()
        RuntimeException exception = thrown()
        exception.message == "User not found with id: ${bookRequest.userId}"
    }


    def "Test getBookById() success case"() {
        given:
        def id = random.nextLong()

        def bookEntity = random.nextObject(BookEntity, "id")
        bookEntity.id = id

        def bookResponse = BookMapper.INSTANCE.buildBookResponse(bookEntity)

        when:
        def response = bookService.getBookById(id)

        then:
        1 * bookRepository.findById(id) >> Optional.of(bookEntity)
        response.id == bookResponse.id
        response.author == bookResponse.author
        response.genre == bookResponse.genre
        response.title == bookResponse.title
        response.status == bookResponse.status
        response.user == bookResponse.user
    }

    def "Test getBookById() error occurred when book not found"() {
        given:
        def id = random.nextLong()
        def bookEntity = random.nextObject(BookEntity, "id")
        bookEntity.id = id

        when:
        bookService.getBookById(id)

        then:
        1 * bookRepository.findById(id) >> Optional.empty()
        RuntimeException exception = thrown()
        exception.message == "Book not found with id: ${id}"
    }
}
