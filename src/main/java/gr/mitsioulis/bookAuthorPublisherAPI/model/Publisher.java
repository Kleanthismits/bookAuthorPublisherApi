package gr.mitsioulis.bookAuthorPublisherAPI.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Publisher {
	@Id
	@GeneratedValue
	private Long   id;
	private String name, telephone, address;
}
