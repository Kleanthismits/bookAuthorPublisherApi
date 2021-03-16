package gr.mitsioulis.bookAuthorPublisherAPI.dto;

import java.time.format.DateTimeFormatter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import gr.mitsioulis.bookAuthorPublisherAPI.annotations.ValidDate;
import gr.mitsioulis.bookAuthorPublisherAPI.model.Author;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class AuthorDTO {

	@NotBlank(message = "First name is required")
	private String firstName;
	@NotBlank(message = "Last name is required")
	private String lastName;
	@Email
	private String emailAddress;
	@ValidDate(optional = true)
	private String birthDate;

	public AuthorDTO(Author author) {
		this.firstName = author.getFirstName();
		this.lastName = author.getLastName();
		this.emailAddress = author.getEmailAddress();
		this.birthDate = author.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
}
