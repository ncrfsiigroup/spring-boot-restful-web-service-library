package cl.siigroup.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.siigroup.libraryapi.model.BookModel;

/**
* Interface to manage BOOK table data in the database
* 
* @author Nelson Ramirez
* 
*/

public interface BookJPARepository extends JpaRepository<BookModel, Integer> {

}
