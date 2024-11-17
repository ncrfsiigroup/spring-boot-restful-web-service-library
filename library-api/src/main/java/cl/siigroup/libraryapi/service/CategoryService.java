package cl.siigroup.libraryapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cl.siigroup.libraryapi.model.CategoryModel;
import cl.siigroup.libraryapi.repository.CategoryJPARepository;

/**
* Connects to the repository that manages CATEGORY table data in the database for business logic defining the operations that will be used
* 
* @author Nelson Ramirez
* 
*/

@Service
@Primary
public class CategoryService {
	
	private final CategoryJPARepository repository;
	
	public CategoryService(CategoryJPARepository repository) {
		this.repository = repository;
	}
	
	public List<CategoryModel> getCategories() {
		return this.repository.findAll(Sort.by("id").ascending());
	}
	
	public void createCategory(CategoryModel category) {
		this.repository.save(category);
	}
	
	public CategoryModel getCategoryById(Integer id) {
		Optional<CategoryModel> optional = repository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public void deleteCategoryById(Integer id) {
		this.repository.deleteById(id);
	}

}
