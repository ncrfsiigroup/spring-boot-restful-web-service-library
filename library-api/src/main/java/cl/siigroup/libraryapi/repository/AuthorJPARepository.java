package cl.siigroup.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.siigroup.libraryapi.model.AuthorModel;

/**
* Interface to manage AUTHOR table data in the database
* 
* @author Nelson Ramirez
* 
*/

public interface AuthorJPARepository extends JpaRepository<AuthorModel, Integer> {

}
