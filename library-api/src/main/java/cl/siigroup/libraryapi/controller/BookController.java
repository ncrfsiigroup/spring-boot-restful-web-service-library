package cl.siigroup.libraryapi.controller;

import java.util.List;

/**
* RESTful Web Service running CRUD operations on BOOK table
* 
* @author Nelson Ramirez
* 
*/

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.siigroup.libraryapi.model.AuthorModel;
import cl.siigroup.libraryapi.model.BookModel;
import cl.siigroup.libraryapi.model.CategoryModel;
import cl.siigroup.libraryapi.service.BookService;
import cl.siigroup.libraryapi.util.Utilities;

@RestController
@RequestMapping("/api/v1")
public class BookController {
	
	// ***Books***
	private final BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	// Read books
	@GetMapping("/books")
	public List<BookModel> books() {
		return this.bookService.getBooks();
	}
	
	// Read book by id
	@GetMapping("/books/{id}")
	public BookModel bookById(@PathVariable Integer id) {
		return this.bookService.getBookById(id);
	}
	
	// Create book
	@PostMapping("/books")
	public ResponseEntity<Object> createBook(@RequestBody BookModel book) {
		try {
			this.bookService.createBook(book);
			
			return Utilities.generateResponse(HttpStatus.CREATED, "Book created successfully");
		} catch (Exception e) {
			return Utilities.generateResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	// Update book
	@PutMapping("/books/{id}")
	public ResponseEntity<Object> updateBook(@PathVariable Integer id, @RequestBody BookModel request) {
		BookModel book = this.bookService.getBookById(id);
		if (request.getTitle() != null) {
			book.setTitle(request.getTitle());
		}
		if (request.getPublisher() != null) {
			book.setPublisher(request.getPublisher());
		}
		if (request.getAuthorId() != null) {
			AuthorModel author = new AuthorModel();
			author.setId(request.getAuthorId().getId());
			book.setAuthorId(author);
		}
		if (request.getCategoryId() != null) {
			CategoryModel category = new CategoryModel();
			category.setId(request.getCategoryId().getId());
			book.setCategoryId(category);
		}
		this.bookService.createBook(book);
		
		return Utilities.generateResponse(HttpStatus.OK, "Book updated successfully");
	}
	
	// Delete book
	@DeleteMapping("/books/{id}")
	public ResponseEntity<Object> deleteBook(@PathVariable Integer id) {
		try {
			this.bookService.deleteBookById(id);
			
			return Utilities.generateResponse(HttpStatus.OK, "Book deleted successfully");
		} catch (Exception e) {
			return Utilities.generateResponse(HttpStatus.BAD_REQUEST, "Book could not be deleted");
		}
	}

}
