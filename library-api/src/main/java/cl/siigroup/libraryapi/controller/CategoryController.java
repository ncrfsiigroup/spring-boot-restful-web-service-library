package cl.siigroup.libraryapi.controller;

import java.util.List;

/**
* RESTful Web Service running CRUD operations on CATEGORY table
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

import cl.siigroup.libraryapi.model.CategoryModel;
import cl.siigroup.libraryapi.service.CategoryService;
import cl.siigroup.libraryapi.util.Utilities;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
	
	// ***Categories***
	private final CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	// Read categories
	@GetMapping("/categories")
	public List<CategoryModel> categories() {
		return this.categoryService.getCategories();
	}
	
	// Read category by id
	@GetMapping("/categories/{id}")
	public CategoryModel categoryById(@PathVariable Integer id) {
		return this.categoryService.getCategoryById(id);
	}
	
	// Create category
	@PostMapping("/categories")
	public ResponseEntity<Object> createCategory(@RequestBody CategoryModel category) {
		this.categoryService.createCategory(category);
		
		return Utilities.generateResponse(HttpStatus.CREATED, "Category created successfully");
	}
	
	// Update category
	@PutMapping("/categories/{id}")
	public ResponseEntity<Object> updateCategory(@PathVariable Integer id, @RequestBody CategoryModel request) {
		CategoryModel category = this.categoryService.getCategoryById(id);
		if (request.getNameCategory() != null) {
			category.setNameCategory(request.getNameCategory());
		}
		this.categoryService.createCategory(category);
		
		return Utilities.generateResponse(HttpStatus.OK, "Category updated successfully");
	}
	
	// Delete category
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<Object> deleteCategory(@PathVariable Integer id) {
		try {
			this.categoryService.deleteCategoryByIdFromBook(id);
			this.categoryService.deleteCategoryById(id);
			
			return Utilities.generateResponse(HttpStatus.OK, "Category deleted successfully");
		} catch (Exception e) {
			return Utilities.generateResponse(HttpStatus.BAD_REQUEST, "Category could not be deleted");
		}
	}

}
