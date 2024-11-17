package cl.siigroup.libraryapi.controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.siigroup.libraryapi.model.AuthorModel;
import cl.siigroup.libraryapi.model.BookModel;
import cl.siigroup.libraryapi.model.CategoryModel;
import cl.siigroup.libraryapi.service.BookService;

@WebMvcTest(BookController.class)
class BookControllerTests {
	
	private static final String END_POINT_PATH = "/api/v1";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private BookService service;
	
	@Test
	void testBooks() throws Exception {
		AuthorModel author = new AuthorModel();
		author.setId(1);
		author.setNameAuthor("Julio Verne");
		
		CategoryModel category = new CategoryModel();
		category.setId(1);
		category.setNameCategory("Aventuras");
		
		BookModel book = new BookModel();
		book.setId(1);
		book.setTitle("Viaje al centro de la tierra");
		book.setPublisher("Lexus Ediciones");
		book.setAuthorId(author);
		book.setCategoryId(category);
		
		List<BookModel> listBook = List.of(book);
		
		Mockito.when(service.getBooks()).thenReturn(listBook);
		
		mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_PATH+"/books"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Viaje al centro de la tierra"));
	}
	
	@Test
	void testBookById() throws Exception {
		Integer id = 1;
		
		AuthorModel author = new AuthorModel();
		author.setId(1);
		author.setNameAuthor("Julio Verne");
		
		CategoryModel category = new CategoryModel();
		category.setId(1);
		category.setNameCategory("Aventuras");
		
		BookModel book = new BookModel();
		book.setId(id);
		book.setTitle("Viaje al centro de la tierra");
		book.setPublisher("Lexus Ediciones");
		book.setAuthorId(author);
		book.setCategoryId(category);
		
		Mockito.when(service.getBookById(id)).thenReturn(book);
		
		mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_PATH+"/books/"+id))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Viaje al centro de la tierra"));
	}
	
	@Test
	void testCreateBook() throws Exception {
		AuthorModel author = new AuthorModel();
		author.setId(1);
		author.setNameAuthor("Julio Verne");
		
		CategoryModel category = new CategoryModel();
		category.setId(1);
		category.setNameCategory("Aventuras");
		
		BookModel book = new BookModel();
		book.setTitle("Viaje al centro de la tierra");
		book.setPublisher("Lexus Ediciones");
		book.setAuthorId(author);
		book.setCategoryId(category);
		
		Mockito.doNothing().when(service).createBook(book);
		
		String requestBody = objectMapper.writeValueAsString(book);
		
		mockMvc.perform(MockMvcRequestBuilders.post(END_POINT_PATH+"/books").contentType("application/json")
			.content(requestBody))
	        .andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	void testCreateBookException() throws Exception {
		AuthorModel author = new AuthorModel();
		author.setId(1);
		author.setNameAuthor("Julio Verne");
		
		CategoryModel category = new CategoryModel();
		category.setId(1);
		category.setNameCategory("Aventuras");
		
		BookModel book = new BookModel();
		book.setTitle("Viaje al centro de la tierra");
		book.setPublisher("Lexus Ediciones");
		book.setAuthorId(author);
		book.setCategoryId(category);
		
		Mockito.doThrow(Exception.class);
		
		String requestBody = objectMapper.writeValueAsString(book);
		
		mockMvc.perform(MockMvcRequestBuilders.post(END_POINT_PATH+"/books").contentType("application/json")
			.content(requestBody))
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	void testUpdateBook() throws Exception {
		Integer id = 1;
		
		AuthorModel author = new AuthorModel();
		author.setId(1);
		author.setNameAuthor("Julio Verne");
		
		CategoryModel category = new CategoryModel();
		category.setId(1);
		category.setNameCategory("Aventuras");
		
		BookModel book = new BookModel();
		book.setId(id);
		book.setTitle("Viaje al centro de la tierra");
		book.setPublisher("Lexus Ediciones");
		book.setAuthorId(author);
		book.setCategoryId(category);
		
		Mockito.when(service.getBookById(id)).thenReturn(book);
		
		book.setPublisher("Lexus");
		
		Mockito.doNothing().when(service).createBook(book);
		
		String requestBody = objectMapper.writeValueAsString(book);
		
		mockMvc.perform(MockMvcRequestBuilders.put(END_POINT_PATH+"/books/"+id).contentType("application/json")
			.content(requestBody))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void testDeleteBook() throws Exception {
		Integer id = 1;
		
		Mockito.doNothing().when(service).deleteBookById(id);
		
		mockMvc.perform(MockMvcRequestBuilders.delete(END_POINT_PATH+"/books/"+id))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void testDeleteBookException() throws Exception {
		Integer id = 1;
		
		Mockito.doThrow(Exception.class);
		
		mockMvc.perform(MockMvcRequestBuilders.delete(END_POINT_PATH+"/books/"+id))
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

}
