package com.spring.library.services.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.library.entities.Librarian;
import com.spring.library.repository.LibrarianRepository;
import com.spring.library.services.LibrarianService;

@Service
public class LibrarianServiceImpl implements LibrarianService {

	private LibrarianRepository repository;
	public LibrarianServiceImpl(LibrarianRepository repository) {
		super();
		this.repository = repository;
	}

	
	@Override
	public List<Librarian> getAllLibrarians() {
		return (List<Librarian>) repository.findAll();
	}

	@Override
	public Librarian saveLibrarian(Librarian librarian) {
		return repository.save(librarian);
	}

	@Override
	public Librarian getLibrarianById(String id) {
		return repository.findById(id).get();
	}


	@Override
	public List<Librarian> getLibrarianByIdAndPassword(String id, String password) {
		return repository.getLibrarianByIdAndPassword(id, password);
	}

}
