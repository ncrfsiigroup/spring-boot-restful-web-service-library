package cl.siigroup.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import cl.siigroup.libraryapi.model.CategoryModel;

/**
* Interface to manage CATEGORY table data in the database
* 
* @author Nelson Ramirez
* 
*/

public interface CategoryJPARepository extends JpaRepository<CategoryModel, Integer> {
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM Book WHERE category_id = :id", nativeQuery = true)
	int bookDeleteByCategoryId(Integer id);

}
