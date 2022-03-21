package com.spring.library.services;

import java.util.List;
import com.spring.library.entities.Librarian;

public interface LibrarianService {

	
	List<Librarian> getAllLibrarians();
		
	
	Librarian saveLibrarian(Librarian librarian);
		
	
	Librarian getLibrarianById(String id);
	
	List<Librarian> getLibrarianByIdAndPassword(String id, String password);
}
