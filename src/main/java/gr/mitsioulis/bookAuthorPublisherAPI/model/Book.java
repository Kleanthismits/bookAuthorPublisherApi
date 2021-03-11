package gr.mitsioulis.bookAuthorPublisherAPI.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Book {
	@Id
	@GeneratedValue
	private Long         id;
	private String       title, description;
	private Boolean      visibilityStatus;
	private LocalDate    creationDate;
	private Long         isbn;
	private List<Author> authors;
	private Publisher    publisher;
}
