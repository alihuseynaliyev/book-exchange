package com.alinazim.bookexchange.dao.repository;

import com.alinazim.bookexchange.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
