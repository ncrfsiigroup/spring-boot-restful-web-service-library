package cl.siigroup.libraryapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cl.siigroup.libraryapi.model.BookModel;
import cl.siigroup.libraryapi.repository.BookJPARepository;

/**
* Connects to the repository that manages BOOK table data in the database for business logic defining the operations that will be used
* 
* @author Nelson Ramirez
* 
*/

@Service
@Primary
public class BookService {
	
	private final BookJPARepository repository;
	
	public BookService(BookJPARepository repository) {
		this.repository = repository;
	}
	
	public List<BookModel> getBooks() {
		return this.repository.findAll(Sort.by("id").ascending());
	}
	
	public void createBook(BookModel book) {
		this.repository.save(book);
	}
	
	public BookModel getBookById(Integer id) {
		Optional<BookModel> optional = repository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public void deleteBookById(Integer id) {
		this.repository.deleteById(id);
	}
	
}
