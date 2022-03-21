package com.spring.library.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.spring.library.custommsg.CustomMessage;
import com.spring.library.entities.Author;
import com.spring.library.entities.Book;
import com.spring.library.services.BookService;

@RestController
public class BookController {

	private BookService bookService;

	public BookController(BookService bookService) {
		super();
		this.bookService = bookService;
	}
	
	@Autowired
	public RestTemplate restTemplate;
	
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/login");
		return modelAndView;
	}
	
	//handler method on controller
	//list out all books details and return model and view(jsp)
	
	@GetMapping("/books")
	public ModelAndView listBooks(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		model.addAttribute("books", bookService.getAllBooks());
		modelAndView.setViewName("books.html");
		return modelAndView;		
	}
	
	//To add new books
	
	@GetMapping("/books/add")
	public ModelAndView addNewBook(Model model) {
		ModelAndView modelAndView = new ModelAndView();

		String restUrl = "http://localhost:9899/authors";

		@SuppressWarnings("unchecked")
		List<Author> authors = restTemplate.getForObject(restUrl, List.class);
		List<String> author = new ArrayList<>();
		for(Object obj : authors) {
			String str = obj.toString();
			String name = str.substring(12, str.length()-1);
			author.add(name);
		}
		
		//book object to hold form data
		Book book = new Book();
		model.addAttribute("book",book);
		model.addAttribute("authorList", author);
		modelAndView.setViewName("addBook.html");
		return modelAndView;
	}
	
	@PostMapping("/books")
	public ModelAndView saveBook(@ModelAttribute("book") Book book, BindingResult result, Model model, HttpSession session) {
	
		try {
		
		ModelAndView modelAndView = new ModelAndView();
		bookService.saveBook(book);
		modelAndView.setViewName("redirect:/books");
		return modelAndView;
		
		} catch (Exception e) {
			e.printStackTrace();
			ModelAndView modelAndView = new ModelAndView();
			model.addAttribute("book", book);
			session.setAttribute("customMessage", new CustomMessage("Bookcode must be unique. Cannot add the book!!","alert-danger"));
			modelAndView.setViewName("redirect:/books/add");
			return modelAndView;
		}
		
	}
	
	//edit the book details
	
	@GetMapping("/books/edit/{id}")
	public ModelAndView editBookDetails(@PathVariable Integer id, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		model.addAttribute("book", bookService.getBookById(id));
		String restUrl = "http://localhost:9899/authors";

		@SuppressWarnings("unchecked")
		List<Author> authors = restTemplate.getForObject(restUrl, List.class);
		List<String> author = new ArrayList<>();
		for(Object obj : authors) {
			String str = obj.toString();
			String name = str.substring(12, str.length()-1);
			author.add(name);
		}
		model.addAttribute("authorList", author);
		modelAndView.setViewName("editBook.html");
		return modelAndView;
	}
	
	
	//handler to handle the edit book details
	
	@PostMapping("/books/{id}")
	public ModelAndView editBook(@PathVariable Integer id, @ModelAttribute("book") Book book, Model model) {
		ModelAndView modelAndView = new ModelAndView();

		//get book details from db with id
		
		Book existingBook = bookService.getBookById(id);
		existingBook.setId(book.getId());
		existingBook.setBookName(book.getBookName());
		existingBook.setAuthor(book.getAuthor());
		existingBook.setDataAdded(book.getDataAdded());
		existingBook.setBookCode(book.getBookCode());
		
		//save the details
		
		bookService.editBook(existingBook);
		modelAndView.setViewName("redirect:/books");
		
		return modelAndView;
		
	}
	
	
	//handler method to delete book details
	
	@GetMapping("/books/{id}")
	public ModelAndView deleteBook(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView();

		bookService.deleteBookById(id);
		modelAndView.setViewName("redirect:/books");
		return modelAndView;
		
	}
	
}
