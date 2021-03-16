package gr.mitsioulis.bookAuthorPublisherAPI.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import gr.mitsioulis.bookAuthorPublisherAPI.model.Publisher;
import gr.mitsioulis.bookAuthorPublisherAPI.utils.StringUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PublisherDTO {

	@NotBlank(message = "Name is required")
	private String name;
	@Size(min = 10, max = 30)
	private String telephone;
	private String address;

	public PublisherDTO(Publisher publisher) {
		this.name = publisher.getName();
		this.telephone = StringUtils.getCleanPhoneNumber(publisher.getTelephone());
		this.address = publisher.getAddress();
	}

}
