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
import cl.siigroup.libraryapi.service.AuthorService;

@WebMvcTest(AuthorController.class)
class AuthorControllerTests {
	
	private static final String END_POINT_PATH = "/api/v1";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private AuthorService service;
	
	@Test
	void testAuthors() throws Exception {
		AuthorModel author = new AuthorModel();
		author.setId(1);
		author.setNameAuthor("Julio Verne");
		
		List<AuthorModel> listAuthor = List.of(author);
		
		Mockito.when(service.getAuthors()).thenReturn(listAuthor);
		
		mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_PATH+"/authors"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].nameAuthor").value("Julio Verne"));
	}
	
	@Test
	void testAuthorById() throws Exception {
		Integer id = 1;
		
		AuthorModel author = new AuthorModel();
		author.setId(id);
		author.setNameAuthor("Julio Verne");
		
		Mockito.when(service.getAuthorById(id)).thenReturn(author);
		
		mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_PATH+"/authors/"+id))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.nameAuthor").value("Julio Verne"));
	}
	
	@Test
	void testCreateAuthor() throws Exception {
		AuthorModel author = new AuthorModel();
		author.setNameAuthor("Julio Verne");
		
		Mockito.doNothing().when(service).createAuthor(author);
		
		String requestBody = objectMapper.writeValueAsString(author);
		
		mockMvc.perform(MockMvcRequestBuilders.post(END_POINT_PATH+"/authors").contentType("application/json")
			.content(requestBody))
	        .andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	void testUpdateAuthor() throws Exception {
		Integer id = 1;
		
		AuthorModel author = new AuthorModel();
		author.setId(id);
		author.setNameAuthor("Julio Verne");
		
		Mockito.when(service.getAuthorById(id)).thenReturn(author);
		
		author.setNameAuthor("Daniel Defoe");
		
		Mockito.doNothing().when(service).createAuthor(author);
		
		String requestBody = objectMapper.writeValueAsString(author);
		
		mockMvc.perform(MockMvcRequestBuilders.put(END_POINT_PATH+"/authors/"+id).contentType("application/json")
			.content(requestBody))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void testDeleteAuthor() throws Exception {
		Integer id = 1;
		
		Mockito.doNothing().when(service).deleteAuthorById(id);
		
		mockMvc.perform(MockMvcRequestBuilders.delete(END_POINT_PATH+"/authors/"+id))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void testDeleteAuthorException() throws Exception {
		Integer id = 1;
		
		Mockito.doThrow(Exception.class);
		
		mockMvc.perform(MockMvcRequestBuilders.delete(END_POINT_PATH+"/authors/"+id))
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

}
