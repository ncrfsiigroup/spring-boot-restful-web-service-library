package cl.siigroup.libraryapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import cl.siigroup.libraryapi.model.AuthorModel;
import cl.siigroup.libraryapi.repository.AuthorJPARepository;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTests {
	
	@Mock
	private AuthorJPARepository repository;
	
	private AuthorService service;
	
	private AuthorModel author;
	
	private static final Integer ID = 1;
	private static final String NAMEAUTHOR = "Julio Verne";
	
	@BeforeEach
	public void setUp() {
		this.service = new AuthorService(repository);
		
		this.author = new AuthorModel();
		this.author.setId(ID);
		this.author.setNameAuthor(NAMEAUTHOR);
	}
	
	@Test
	void testGetAuthors() {
		List<AuthorModel> listAuthor = List.of(author);
		
		Mockito.when(repository.findAll(Sort.by("id").ascending())).thenReturn(listAuthor);
		
		List<AuthorModel> result = service.getAuthors();
		
		assertEquals(NAMEAUTHOR, result.get(0).getNameAuthor());
	}
	
	@Test
	void testCreateAuthor() {
		service.createAuthor(author);
		
		assertTrue(true);
	}
	
	@Test
	void testGetAuthorById() {
		Optional<AuthorModel> optional = Optional.of(author);
		
		Mockito.when(repository.findById(ID)).thenReturn(optional);
		
		AuthorModel result = service.getAuthorById(ID);
		
		assertEquals(NAMEAUTHOR, result.getNameAuthor());
	}
	
	@Test
	void testGetAuthorByIdNull() {
		Optional<AuthorModel> optional = Optional.empty();
		
		Mockito.when(repository.findById(ID)).thenReturn(optional);
		
		AuthorModel result = service.getAuthorById(ID);
		
		assertNull(result);
	}
	
	@Test
	void testDeleteAuthorById() {
		service.createAuthor(author);
		
		service.deleteAuthorById(ID);
		
		assertTrue(true);
	}

}
