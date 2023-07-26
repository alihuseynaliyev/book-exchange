package com.alinazim.bookexchange.dao.repository;

import com.alinazim.bookexchange.dao.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
