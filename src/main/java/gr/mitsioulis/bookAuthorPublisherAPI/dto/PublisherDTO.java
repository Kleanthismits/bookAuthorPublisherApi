package gr.mitsioulis.bookAuthorPublisherAPI.dto;

import gr.mitsioulis.bookAuthorPublisherAPI.model.Publisher;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PublisherDTO {

	private String name;
	private String telephone;
	private String address;

	public PublisherDTO(Publisher publisher) {
		this.name = publisher.getName();
		this.telephone = publisher.getTelephone();
		this.address = publisher.getAddress();
	}

}
