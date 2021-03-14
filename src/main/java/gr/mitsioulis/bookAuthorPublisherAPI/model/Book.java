package gr.mitsioulis.bookAuthorPublisherAPI.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import gr.mitsioulis.bookAuthorPublisherAPI.dto.BookDTO;
import gr.mitsioulis.bookAuthorPublisherAPI.utils.ISBNUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "books", uniqueConstraints = @UniqueConstraint(columnNames = { "isbn" }))
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long      id;
	@NotNull
	private String    title;
	@NotNull
	@Column(length = 65535)
	private String    description;
	private Boolean   visibilityStatus;
	@Column(columnDefinition = "DATE")
	private LocalDate creationDate;
	@Min(value = 9780000000001l)
	private Long      isbn;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "author_id", nullable = false)
	private Author    author;
	@ManyToOne
	@JoinColumn(name = "publisher_id", nullable = true)
	private Publisher publisher;

	public Book(BookDTO bookDTO) {
		this.title = bookDTO.getTitle();
		this.description = bookDTO.getDescription();
		this.visibilityStatus = bookDTO.isVisible();
		this.creationDate = LocalDate.parse(bookDTO.getCreationDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.isbn = ISBNUtils.getISBNFromPresentation(bookDTO.getIsbn());
		this.author = bookDTO.getAuthor();
		this.publisher = bookDTO.getPublisher();
	}
}
