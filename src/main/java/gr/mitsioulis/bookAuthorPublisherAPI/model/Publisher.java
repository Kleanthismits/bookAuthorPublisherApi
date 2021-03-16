package gr.mitsioulis.bookAuthorPublisherAPI.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import gr.mitsioulis.bookAuthorPublisherAPI.dto.PublisherDTO;
import lombok.Data;
import lombok.ToString.Exclude;

@Entity
@Data
@Table(name = "publishers")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Publisher.class)
@JsonInclude(value = Include.NON_NULL)
public class Publisher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long      id;
	private String    name, telephone, address;
	@OneToMany(mappedBy = "publisher")
	@Exclude
	private Set<Book> books;

	public Publisher() {
	}

	public Publisher(PublisherDTO publisherDTO) {
		this.name = publisherDTO.getName();
		this.telephone = publisherDTO.getTelephone();
		this.address = publisherDTO.getAddress();
	}
}
