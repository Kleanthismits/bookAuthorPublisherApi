package gr.mitsioulis.bookAuthorPublisherAPI.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Author {

	@Id
	@GeneratedValue
	private Long      id;
	private String    firstName, lastName, emailAddress;
	private LocalDate birthDate;

}
