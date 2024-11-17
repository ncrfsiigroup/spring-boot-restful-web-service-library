package cl.siigroup.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import cl.siigroup.libraryapi.model.AuthorModel;

/**
* Interface to manage AUTHOR table data in the database
* 
* @author Nelson Ramirez
* 
*/

public interface AuthorJPARepository extends JpaRepository<AuthorModel, Integer> {
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM Book WHERE author_id = :id", nativeQuery = true)
	int bookDeleteByAuthorId(Integer id);

}
