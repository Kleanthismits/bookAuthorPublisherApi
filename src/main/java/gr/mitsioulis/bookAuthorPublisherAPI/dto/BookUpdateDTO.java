package gr.mitsioulis.bookAuthorPublisherAPI.dto;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import gr.mitsioulis.bookAuthorPublisherAPI.annotations.ValidAuthor;
import gr.mitsioulis.bookAuthorPublisherAPI.annotations.ValidDate;
import gr.mitsioulis.bookAuthorPublisherAPI.annotations.ValidISBN;
import gr.mitsioulis.bookAuthorPublisherAPI.annotations.ValidPublisher;
import gr.mitsioulis.bookAuthorPublisherAPI.model.Author;
import gr.mitsioulis.bookAuthorPublisherAPI.model.Book;
import gr.mitsioulis.bookAuthorPublisherAPI.model.Publisher;
import gr.mitsioulis.bookAuthorPublisherAPI.utils.ISBNUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = BookUpdateDTO.class)
public class BookUpdateDTO {

	private Long      id;
	private String    title;
	@Column(length = 65535)
	private String    description;
	private Boolean   visibilityStatus;
	@ValidDate(optional = true)
	private String    creationDate;
	@ValidISBN(optional = true, checkDuplicate = false)
	private String    isbn;
	@ValidAuthor(optional = true)
	@JsonIgnoreProperties({ "books", "firstName", "lastName", "emailAddress", "birthDate" })
	private Author    author;
	@ValidPublisher(optional = true)
	@JsonIgnoreProperties({ "books", "name", "telephone", "address" })
	private Publisher publisher;

	public BookUpdateDTO(Book book) {
		this.id = book.getId();
		this.title = book.getTitle();
		this.description = book.getDescription();
		this.visibilityStatus = book.getVisibilityStatus();
		this.isbn = ISBNUtils.getPresentationISBN(book.getIsbn());
		this.author = book.getAuthor();
		this.publisher = book.getPublisher();
	}
}
