package gr.mitsioulis.bookAuthorPublisherAPI.annotations;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gr.mitsioulis.bookAuthorPublisherAPI.model.Publisher;
import gr.mitsioulis.bookAuthorPublisherAPI.service.PublisherService;

@Component
public class ValidPublisherValidator implements ConstraintValidator<ValidPublisher, Publisher> {

	@Autowired
	PublisherService publisherService;

	private Boolean isOptional;

	@Override
	public void initialize(ValidPublisher validPublisher) {
		this.isOptional = validPublisher.optional();
	}

	@Override
	public boolean isValid(Publisher value, ConstraintValidatorContext context) {
		if (isOptional && value == null) {
			return true;
		}

		boolean validPublisher = isValidPublisher(value);
		if (!validPublisher) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("no publisher with id: " + value.getId())
					.addConstraintViolation();
		}
		return validPublisher;
	}

	private boolean isValidPublisher(Publisher value) {
		Optional<Publisher> pub = null;
		if (value != null && value.getId() != null) {
			pub = publisherService.findById(value.getId());
		}
		return pub == null ? false : pub.isPresent();
	}

}
