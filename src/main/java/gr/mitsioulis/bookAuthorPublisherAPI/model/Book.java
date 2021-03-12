package gr.mitsioulis.bookAuthorPublisherAPI.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "books", uniqueConstraints = @UniqueConstraint(columnNames = { "isbn" }))
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
	@NotNull
	@Min(value = 9780000000001l)
	private Long      isbn;
	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	@JsonManagedReference
	private Author    author;
	@ManyToOne
	@JoinColumn(name = "publisher_id", nullable = true)
	@JsonManagedReference
	private Publisher publisher;
	@Transient
	@JsonIgnore
	private Long      authorPositionAttribute;

}
