package com.spring.library.services;

import java.util.List;

import com.spring.library.entities.Book;

public interface BookService {

	//list all books
	List<Book> getAllBooks();
	
	//save a book
	Book saveBook(Book book);
	
	//to get a book by its id
	Book getBookById(Integer id);
	
	//edit the book details
	Book editBook(Book book);
	
	//delete the book
	void deleteBookById(Integer id);
	

	
}
