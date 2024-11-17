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

import cl.siigroup.libraryapi.model.CategoryModel;
import cl.siigroup.libraryapi.service.CategoryService;

@WebMvcTest(CategoryController.class)
class CategoryControllerTests {
	
	private static final String END_POINT_PATH = "/api/v1";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private CategoryService service;
	
	@Test
	void testCategories() throws Exception {
		CategoryModel category = new CategoryModel();
		category.setId(1);
		category.setNameCategory("Aventuras");
		
		List<CategoryModel> listCategory = List.of(category);
		
		Mockito.when(service.getCategories()).thenReturn(listCategory);
		
		mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_PATH+"/categories"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].nameCategory").value("Aventuras"));
	}
	
	@Test
	void testCategoryById() throws Exception {
		Integer id = 1;
		
		CategoryModel category = new CategoryModel();
		category.setId(id);
		category.setNameCategory("Aventuras");
		
		Mockito.when(service.getCategoryById(id)).thenReturn(category);
		
		mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_PATH+"/categories/"+id))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.nameCategory").value("Aventuras"));
	}
	
	@Test
	void testCreateCategory() throws Exception {
		CategoryModel category = new CategoryModel();
		category.setNameCategory("Aventuras");
		
		Mockito.doNothing().when(service).createCategory(category);
		
		String requestBody = objectMapper.writeValueAsString(category);
		
		mockMvc.perform(MockMvcRequestBuilders.post(END_POINT_PATH+"/categories").contentType("application/json")
			.content(requestBody))
	        .andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	void testUpdateCategory() throws Exception {
		Integer id = 1;
		
		CategoryModel category = new CategoryModel();
		category.setId(id);
		category.setNameCategory("Aventuras");
		
		Mockito.when(service.getCategoryById(id)).thenReturn(category);
		
		category.setNameCategory("Terror");
		
		Mockito.doNothing().when(service).createCategory(category);
		
		String requestBody = objectMapper.writeValueAsString(category);
		
		mockMvc.perform(MockMvcRequestBuilders.put(END_POINT_PATH+"/categories/"+id).contentType("application/json")
			.content(requestBody))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void testDeleteCategory() throws Exception {
		Integer id = 1;
		
		Mockito.doNothing().when(service).deleteCategoryById(id);
		
		mockMvc.perform(MockMvcRequestBuilders.delete(END_POINT_PATH+"/categories/"+id))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testDeleteCategoryException() throws Exception {
		Integer id = 1;
		
		Mockito.doThrow(Exception.class);
		
		mockMvc.perform(MockMvcRequestBuilders.delete(END_POINT_PATH+"/categories/"+id))
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
}
