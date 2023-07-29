package mapper

import com.alinazim.bookexchange.dao.entity.UserEntity
import com.alinazim.bookexchange.mapper.BookMapper
import com.alinazim.bookexchange.model.request.BookRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class BookMapperTest extends Specification {
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def "Test buildBookEntity()"() {
        given:
        def bookRequest = random.nextObject(BookRequest)
        def userEntity = random.nextObject(UserEntity)

        when:
        def result = BookMapper.INSTANCE.buildBookEntity(bookRequest, userEntity)

        then:
        result.title == bookRequest.title
        result.author == bookRequest.author
        result.genre == bookRequest.genre
        result.user == userEntity
    }
}
