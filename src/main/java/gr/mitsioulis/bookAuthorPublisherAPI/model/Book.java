package gr.mitsioulis.bookAuthorPublisherAPI.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import gr.mitsioulis.bookAuthorPublisherAPI.dto.BookDTO;
import gr.mitsioulis.bookAuthorPublisherAPI.dto.BookUpdateDTO;
import gr.mitsioulis.bookAuthorPublisherAPI.utils.ISBNUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "books", uniqueConstraints = @UniqueConstraint(columnNames = { "isbn" }))
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Book.class)
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long      id;
	@NotNull
	@Column(length = 500)
	private String    title;
	@NotNull
	@Column(length = 3000)
	private String    description;
	private Boolean   visibilityStatus;
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate creationDate;
	@Min(value = 9780000000001l)
	private Long      isbn;
	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	@JsonIgnoreProperties("books")
	private Author    author;
	@ManyToOne
	@JoinColumn(name = "publisher_id", nullable = true)
	@JsonIgnoreProperties("books")
	private Publisher publisher;

	public Book(BookDTO bookDTO) {
		this.title = bookDTO.getTitle();
		this.description = bookDTO.getDescription();
		this.visibilityStatus = Boolean.valueOf(bookDTO.getVisibilityStatus());
		this.creationDate = LocalDate.parse(bookDTO.getCreationDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.isbn = ISBNUtils.getISBNFromPresentation(bookDTO.getIsbn());
		this.author = bookDTO.getAuthor();
		this.publisher = bookDTO.getPublisher();
	}

	public void updateBook(BookUpdateDTO bookUpdateDTO) {
		if (bookUpdateDTO.getAuthor() != null) {
			this.setAuthor(bookUpdateDTO.getAuthor());
		}
		if (bookUpdateDTO.getPublisher() != null) {
			this.setPublisher(bookUpdateDTO.getPublisher());
		}
		if (bookUpdateDTO.getTitle() != null) {
			this.setTitle(bookUpdateDTO.getTitle());
		}
		if (bookUpdateDTO.getDescription() != null) {
			this.setDescription(bookUpdateDTO.getDescription());
		}
		if (bookUpdateDTO.getVisibilityStatus() != null) {
			this.setVisibilityStatus(Boolean.valueOf(bookUpdateDTO.getVisibilityStatus()));
		}
		if (bookUpdateDTO.getCreationDate() != null) {
			this.setCreationDate(
					LocalDate.parse(bookUpdateDTO.getCreationDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		}
		if (bookUpdateDTO.getIsbn() != null) {
			this.setIsbn(ISBNUtils.getISBNFromPresentation(bookUpdateDTO.getIsbn()));
		}
	}
}
