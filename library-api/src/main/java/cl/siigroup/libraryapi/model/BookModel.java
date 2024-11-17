package cl.siigroup.libraryapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
* Defines the data that will be used from the fields of the BOOK table
* 
* @author Nelson Ramirez
* 
*/

@Entity
@Table(name="book")
public class BookModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 100, nullable = false)
	private String title;
	
	@Column(length = 100, nullable = false)
	private String publisher;
	
	@OneToOne
	@JoinColumn(name = "author_id")
	private AuthorModel authorId;
	@OneToOne
	@JoinColumn(name = " category_id")
	private CategoryModel categoryId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public AuthorModel getAuthorId() {
		return authorId;
	}
	public void setAuthorId(AuthorModel authorId) {
		this.authorId = authorId;
	}
	public CategoryModel getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(CategoryModel categoryId) {
		this.categoryId = categoryId;
	}

}
