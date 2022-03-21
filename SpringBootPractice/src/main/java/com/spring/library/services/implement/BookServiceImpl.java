package com.spring.library.services.implement;

import java.util.List;
import org.springframework.stereotype.Service;

import com.spring.library.entities.Book;
import com.spring.library.repository.BookRepository;
import com.spring.library.services.BookService;

@Service
public class BookServiceImpl implements BookService {

	
	private BookRepository repository;
	
	public BookServiceImpl(BookRepository repository) {
		super();
		this.repository = repository;
	}


	@Override
	public List<Book> getAllBooks() {
		return (List<Book>) repository.findAll();		
	}


	@Override
	public Book saveBook(Book book) {
		return repository.save(book);
	}


	@Override
	public Book getBookById(Integer id) {
		return repository.findById(id).get();
	}


	@Override
	public Book editBook(Book book) {
		return repository.save(book);
	}


	@Override
	public void deleteBookById(Integer id) {
		repository.deleteById(id);		
	}

}
