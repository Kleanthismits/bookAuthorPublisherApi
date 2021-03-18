package gr.mitsioulis.bookAuthorPublisherAPI.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.group.GroupSequenceProvider;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import gr.mitsioulis.bookAuthorPublisherAPI.annotations.ValidAuthor;
import gr.mitsioulis.bookAuthorPublisherAPI.annotations.ValidDate;
import gr.mitsioulis.bookAuthorPublisherAPI.annotations.ValidISBN;
import gr.mitsioulis.bookAuthorPublisherAPI.annotations.ValidPublisher;
import gr.mitsioulis.bookAuthorPublisherAPI.common.validationGroup.BookDTOGroupSequenceProvider;
import gr.mitsioulis.bookAuthorPublisherAPI.common.validationGroup.FirstValidation;
import gr.mitsioulis.bookAuthorPublisherAPI.common.validationGroup.SecondValidation;
import gr.mitsioulis.bookAuthorPublisherAPI.model.Author;
import gr.mitsioulis.bookAuthorPublisherAPI.model.Book;
import gr.mitsioulis.bookAuthorPublisherAPI.model.Publisher;
import gr.mitsioulis.bookAuthorPublisherAPI.utils.ISBNUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
@GroupSequenceProvider(BookDTOGroupSequenceProvider.class)
public class BookDTO {

	@NotBlank(groups = FirstValidation.class, message = "Title is required")
	private String    title;
	@NotBlank(groups = FirstValidation.class, message = "Description is required")
	@Size(max = 3000, message = "should be maximum 100 characters", groups = SecondValidation.class)
	private String    description;
	@Pattern(regexp = "^true$|^false$", message = "allowed input: true or false")
	private String    visibilityStatus;
	@ValidDate(groups = SecondValidation.class)
	@NotBlank(groups = FirstValidation.class, message = "Creation date is required")
	private String    creationDate;
	@NotBlank(groups = FirstValidation.class, message = "ISBN number is required")
	@ValidISBN(groups = SecondValidation.class)
	private String    isbn;
	@NotNull(groups = FirstValidation.class, message = "Author id is required")
	@ValidAuthor(groups = SecondValidation.class)
	private Author    author;
	@ValidPublisher(optional = true, groups = SecondValidation.class)
	private Publisher publisher;

	public BookDTO(Book book) {
		this.title = book.getTitle();
		this.description = book.getDescription();
		this.visibilityStatus = book.getVisibilityStatus().toString();
		this.isbn = ISBNUtils.getPresentationISBN(book.getIsbn());
		this.author = book.getAuthor();
		this.publisher = book.getPublisher();
	}

}
