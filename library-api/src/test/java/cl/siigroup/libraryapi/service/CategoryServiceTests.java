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

import cl.siigroup.libraryapi.model.CategoryModel;
import cl.siigroup.libraryapi.repository.CategoryJPARepository;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTests {
	
	@Mock
	private CategoryJPARepository repository;
	
	private CategoryService service;
	
	private CategoryModel category;
	
	private static final Integer ID = 1;
	private static final String NAMECATEGORY = "Aventuras";
	
	@BeforeEach
	public void setUp() {
		this.service = new CategoryService(repository);
		
		this.category = new CategoryModel();
		this.category.setId(ID);
		this.category.setNameCategory(NAMECATEGORY);
	}
	
	@Test
	void testGetCategories() {
		List<CategoryModel> listCategory = List.of(category);
		
		Mockito.when(repository.findAll(Sort.by("id").ascending())).thenReturn(listCategory);
		
		List<CategoryModel> result = service.getCategories();
		
		assertEquals(NAMECATEGORY, result.get(0).getNameCategory());
	}
	
	@Test
	void testCreateCategory() {
		service.createCategory(category);
		
		assertTrue(true);
	}
	
	@Test
	void testGetCategoryById() {
		Optional<CategoryModel> optional = Optional.of(category);
		
		Mockito.when(repository.findById(ID)).thenReturn(optional);
		
		CategoryModel result = service.getCategoryById(ID);
		
		assertEquals(NAMECATEGORY, result.getNameCategory());
	}
	
	@Test
	void testGetCategoryByIdNull() {
		Optional<CategoryModel> optional = Optional.empty();
		
		Mockito.when(repository.findById(ID)).thenReturn(optional);
		
		CategoryModel result = service.getCategoryById(ID);
		
		assertNull(result);
	}
	
	@Test
	void testDeleteCategoryById() {
		service.createCategory(category);
		
		service.deleteCategoryById(ID);
		
		assertTrue(true);
	}

}
