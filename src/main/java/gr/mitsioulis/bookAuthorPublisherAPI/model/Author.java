package gr.mitsioulis.bookAuthorPublisherAPI.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import gr.mitsioulis.bookAuthorPublisherAPI.dto.AuthorDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

@Data
@NoArgsConstructor
@Entity
@Table(name = "authors")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Author.class)
@JsonInclude(value = Include.NON_NULL)
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long      id;
	private String    firstName;
	private String    lastName;
	private String    emailAddress;
	private LocalDate birthDate;
	@OneToMany(mappedBy = "author")
	@Exclude
	private Set<Book> books;

	public Author(AuthorDTO authorDTO) {
		this.firstName = authorDTO.getFirstName();
		this.lastName = authorDTO.getLastName();
		this.emailAddress = authorDTO.getEmailAddress();
		this.birthDate = LocalDate.parse(authorDTO.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

}
