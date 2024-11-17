package cl.siigroup.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.siigroup.libraryapi.model.CategoryModel;

/**
* Interface to manage CATEGORY table data in the database
* 
* @author Nelson Ramirez
* 
*/

public interface CategoryJPARepository extends JpaRepository<CategoryModel, Integer> {

}
