package gr.mitsioulis.bookAuthorPublisherAPI.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import gr.mitsioulis.bookAuthorPublisherAPI.model.Book;
import gr.mitsioulis.bookAuthorPublisherAPI.utils.DateUtils;
import gr.mitsioulis.bookAuthorPublisherAPI.utils.ISBNUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class SingleBookDTO {

	private String    title;
	private String    description;
	private String    isbn;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate creationDate;
	private String    authorFullName;
	private String    authorEmail;
	private String    authorDateOfBirth;
	private String    publisherName;
	private String    publisherAddress;

	public SingleBookDTO(Book book) {
		this.title = book.getTitle();
		this.description = book.getDescription();
		this.isbn = ISBNUtils.getPresentationISBN(book.getIsbn());
		this.creationDate = book.getCreationDate();
		this.authorFullName = book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName();
		this.authorEmail = book.getAuthor().getEmailAddress();
		this.authorDateOfBirth = DateUtils.getAuthorBirthPresenation(book.getAuthor().getBirthDate());
		this.publisherName = book.getPublisher() != null ? book.getPublisher().getName() : null;
		this.publisherAddress = book.getPublisher() != null ? book.getPublisher().getAddress() : null;
	}
}
