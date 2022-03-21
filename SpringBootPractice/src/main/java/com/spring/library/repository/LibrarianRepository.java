package com.spring.library.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spring.library.entities.Librarian;

public interface LibrarianRepository extends CrudRepository<Librarian, String> {

	@Query("select l From Librarian l where l.id =:n and l.password =:c")
	public List<Librarian> getLibrarianByIdAndPassword(@Param("n")String id, @Param("c") String password);
	
}
