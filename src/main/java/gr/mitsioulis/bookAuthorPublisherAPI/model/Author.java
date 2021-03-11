package gr.mitsioulis.bookAuthorPublisherAPI.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "authors")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long      id;
	private String    firstName, lastName, emailAddress;
	private LocalDate birthDate;
	@OneToMany(mappedBy = "author")
	private Set<Book> book;

}
