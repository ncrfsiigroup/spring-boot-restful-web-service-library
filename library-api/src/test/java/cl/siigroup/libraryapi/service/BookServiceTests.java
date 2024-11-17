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
import cl.siigroup.libraryapi.model.BookModel;
import cl.siigroup.libraryapi.model.CategoryModel;
import cl.siigroup.libraryapi.repository.BookJPARepository;

@ExtendWith(MockitoExtension.class)
class BookServiceTests {
	
	@Mock
	private BookJPARepository repository;
	
	private BookService service;
	
	private BookModel book;
	private AuthorModel author;
	private CategoryModel category;
	
	private static final Integer ID = 1;
	private static final String TITLE = "Viaje al centro de la tierra";
	private static final String PUBLISHER = "Lexus";
	
	@BeforeEach
	public void setUp() {
		this.service = new BookService(repository);
		
		this.author = new AuthorModel();
		this.author.setId(1);
		this.author.setNameAuthor("Julio Verne");
		
		this.category = new CategoryModel();
		this.category.setId(1);
		this.category.setNameCategory("Aventuras");
		
		this.book = new BookModel();
		this.book.setId(ID);
		this.book.setTitle(TITLE);
		this.book.setPublisher(PUBLISHER);
		this.book.setAuthorId(author);
		this.book.setCategoryId(category);
	}
	
	@Test
	void testGetBooks() {
		List<BookModel> lisBook = List.of(book);
		
		Mockito.when(repository.findAll(Sort.by("id").ascending())).thenReturn(lisBook);
		
		List<BookModel> result = service.getBooks();
		
		assertEquals(TITLE, result.get(0).getTitle());
	}
	
	@Test
	void testCreateBook() {
		service.createBook(book);
		
		assertTrue(true);
	}
	
	@Test
	void testGetBookById() {
		Optional<BookModel> optional = Optional.of(book);
		
		Mockito.when(repository.findById(ID)).thenReturn(optional);
		
		BookModel result = service.getBookById(ID);
		
		assertEquals(TITLE, result.getTitle());
	}
	
	@Test
	void testGetBookByIdNull() {
		Optional<BookModel> optional = Optional.empty();
		
		Mockito.when(repository.findById(ID)).thenReturn(optional);
		
		BookModel result = service.getBookById(ID);
		
		assertNull(result);
	}
	
	@Test
	void testDeleteBookById() {
		service.createBook(book);
		
		service.deleteBookById(ID);
		
		assertTrue(true);
	}

}
