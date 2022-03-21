package com.spring.library.repository;

import org.springframework.data.repository.CrudRepository;

import com.spring.library.entities.Book;


public interface BookRepository extends CrudRepository<Book, Integer>{
	
}
