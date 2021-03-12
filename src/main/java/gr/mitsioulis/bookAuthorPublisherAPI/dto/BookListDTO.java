package gr.mitsioulis.bookAuthorPublisherAPI.dto;

import gr.mitsioulis.bookAuthorPublisherAPI.model.Book;
import gr.mitsioulis.bookAuthorPublisherAPI.utils.ISBNUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookListDTO {

	private String title;
	private String description;
	private String authorFullName;
	private String isbn;

	public BookListDTO(Book book) {
		this.title = book.getTitle();
		this.description = book.getDescription().length() <= 100 ? book.getDescription()
				: book.getDescription().substring(0, 100) + "...";
		this.isbn = ISBNUtils.getPresentationISBN(book.getIsbn());
		this.authorFullName = book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName();
	}
}
