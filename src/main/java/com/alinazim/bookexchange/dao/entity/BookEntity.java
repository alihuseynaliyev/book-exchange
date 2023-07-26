package com.alinazim.bookexchange.dao.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.USE_DEFAULTS;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String genre;

    @JsonInclude(USE_DEFAULTS)
    private String status = "CREATED";

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}


