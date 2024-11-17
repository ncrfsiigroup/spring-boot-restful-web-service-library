package cl.siigroup.libraryapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cl.siigroup.libraryapi.model.AuthorModel;
import cl.siigroup.libraryapi.repository.AuthorJPARepository;

/**
* Connects to the repository that manages AUTHOR table data in the database for business logic defining the operations that will be used
* 
* @author Nelson Ramirez
* 
*/

@Service
@Primary
public class AuthorService {
	
	private final AuthorJPARepository repository;
	
	public AuthorService(AuthorJPARepository repository) {
		this.repository = repository;
	}
	
	public List<AuthorModel> getAuthors() {
		return this.repository.findAll(Sort.by("id").ascending());
	}
	
	public void createAuthor(AuthorModel author) {
		this.repository.save(author);
	}
	
	public AuthorModel getAuthorById(Integer id) {
		Optional<AuthorModel> optional = repository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public void deleteAuthorById(Integer id) {
		this.repository.deleteById(id);
	}

}
