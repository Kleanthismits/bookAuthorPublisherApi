package gr.mitsioulis.bookAuthorPublisherAPI.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "publishers")
public class Publisher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long      id;
	private String    name, telephone, address;
	@OneToMany(mappedBy = "publisher")
	private Set<Book> book;
}
