package gr.mitsioulis.bookAuthorPublisherAPI.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
public class BookUpdateDTO {

	@Size(max = 500, message = "should be maximum 500 characters")
	private String    title;
	@Size(max = 3000, message = "should be maximum 3000 characters")
	private String    description;
	@Pattern(regexp = "^true$|^false$", message = "allowed input: 'true' or 'false'")
	private String    visibilityStatus;
	@ValidDate(optional = true)
	private String    creationDate;
	@ValidISBN(optional = true, checkDuplicate = false)
	private String    isbn;
	@ValidAuthor(optional = true)
	@JsonIgnoreProperties({ "books" })
	private Author    author;
	@ValidPublisher(optional = true)
	@JsonIgnoreProperties({ "books" })
	private Publisher publisher;

	public BookUpdateDTO(Book book) {
		this.title = book.getTitle();
		this.description = book.getDescription();
		this.visibilityStatus = book.getVisibilityStatus().toString();
		this.isbn = ISBNUtils.getPresentationISBN(book.getIsbn());
		this.author = book.getAuthor();
		this.publisher = book.getPublisher();
	}
}
