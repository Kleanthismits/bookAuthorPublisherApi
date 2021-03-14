package gr.mitsioulis.bookAuthorPublisherAPI.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import gr.mitsioulis.bookAuthorPublisherAPI.annotations.UniqueISBN;
import gr.mitsioulis.bookAuthorPublisherAPI.annotations.ValidAuthor;
import gr.mitsioulis.bookAuthorPublisherAPI.annotations.ValidDate;
import gr.mitsioulis.bookAuthorPublisherAPI.model.Author;
import gr.mitsioulis.bookAuthorPublisherAPI.model.Book;
import gr.mitsioulis.bookAuthorPublisherAPI.model.Publisher;
import gr.mitsioulis.bookAuthorPublisherAPI.utils.ISBNUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class BookDTO {

	@NotNull
	private String    title;
	@NotNull
	@Column(length = 65535)
	private String    description;
	private boolean   visible;
	@ValidDate
	private String    creationDate;
	@NotNull
	@UniqueISBN
	private String    isbn;
	@NotNull
	@ValidAuthor
	private Author    author;
	private Publisher publisher;

	public BookDTO(Book book) {
		this.title = book.getTitle();
		this.description = book.getDescription();
		this.visible = book.getVisibilityStatus();
		this.isbn = ISBNUtils.getPresentationISBN(book.getIsbn());
		this.author = book.getAuthor();
		this.publisher = book.getPublisher();
	}

}
