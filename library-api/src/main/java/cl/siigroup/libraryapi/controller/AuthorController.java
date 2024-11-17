package cl.siigroup.libraryapi.controller;

import java.util.List;

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
import cl.siigroup.libraryapi.service.AuthorService;
import cl.siigroup.libraryapi.util.Utilities;

/**
* RESTful Web Service running CRUD operations on AUTHOR table
* 
* @author Nelson Ramirez
* 
*/

@RestController
@RequestMapping("/api/v1")
public class AuthorController {
	
	// ***Authors***
	private final AuthorService authorService;
	
	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}
	
	// Read authors
	@GetMapping("/authors")
	public List<AuthorModel> authors() {
		return this.authorService.getAuthors();
	}
	
	// Read author by id
	@GetMapping("/authors/{id}")
	public AuthorModel authorById(@PathVariable Integer id) {
		return this.authorService.getAuthorById(id);
	}
	
	// Create author
	@PostMapping("/authors")
	public ResponseEntity<Object> createAuthor(@RequestBody AuthorModel author) {
		this.authorService.createAuthor(author);
		
		return Utilities.generateResponse(HttpStatus.CREATED, "Author created successfully");
	}
	
	// Update author
	@PutMapping("/authors/{id}")
	public ResponseEntity<Object> updateAuthor(@PathVariable Integer id, @RequestBody AuthorModel request) {
		AuthorModel author = this.authorService.getAuthorById(id);
		if (request.getNameAuthor() != null) {
			author.setNameAuthor(request.getNameAuthor());
		}
		this.authorService.createAuthor(author);
		
		return Utilities.generateResponse(HttpStatus.OK, "Author updated successfully");
	}
	
	// Delete author
	@DeleteMapping("/authors/{id}")
	public ResponseEntity<Object> deleteAuthor(@PathVariable Integer id) {
		try {
			this.authorService.deleteAuthorById(id);
			
			return Utilities.generateResponse(HttpStatus.OK, "Author deleted successfully");
		} catch (Exception e) {
			return Utilities.generateResponse(HttpStatus.BAD_REQUEST, "Author could not be deleted");
		}
	}

}
