package gr.mitsioulis.bookAuthorPublisherAPI.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long      id;
	private String    title, description;
	private Boolean   visibilityStatus;
	private LocalDate creationDate;
	private Long      isbn;
	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private Author    author;
	@ManyToOne
	@JoinColumn(name = "publisher_id", nullable = false)
	private Publisher publisher;
}
