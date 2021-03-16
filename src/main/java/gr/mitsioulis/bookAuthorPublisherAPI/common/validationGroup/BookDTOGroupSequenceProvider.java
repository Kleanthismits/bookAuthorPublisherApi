package gr.mitsioulis.bookAuthorPublisherAPI.common.validationGroup;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import gr.mitsioulis.bookAuthorPublisherAPI.dto.BookDTO;

public class BookDTOGroupSequenceProvider implements DefaultGroupSequenceProvider<BookDTO> {

	@Override
	public List<Class<?>> getValidationGroups(BookDTO object) {
		List<Class<?>> defaultGroupSequence = new ArrayList<>();
		defaultGroupSequence.add(BookDTO.class);
		defaultGroupSequence.add(FirstValidation.class);
		defaultGroupSequence.add(SecondValidation.class);
		defaultGroupSequence.add(ThirdValidation.class);
		return defaultGroupSequence;
	}

}
