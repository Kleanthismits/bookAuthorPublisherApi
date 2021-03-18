package gr.mitsioulis.bookAuthorPublisherAPI.annotations;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gr.mitsioulis.bookAuthorPublisherAPI.model.Author;
import gr.mitsioulis.bookAuthorPublisherAPI.service.AuthorService;

@Component
public class ValidAuthorValidator implements ConstraintValidator<ValidAuthor, Author> {

	@Autowired
	AuthorService authorService;

	private Boolean isOptional;

	@Override
	public void initialize(ValidAuthor validAuthor) {
		this.isOptional = validAuthor.optional();
	}

	@Override
	public boolean isValid(Author value, ConstraintValidatorContext context) {

		if (isOptional && value == null) {
			return true;
		}
		boolean validAuthor = isValidAuthor(value);
		if (!validAuthor) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("no author with id: " + value.getId()).addConstraintViolation();
		}
		return validAuthor;
	}

	private boolean isValidAuthor(Author value) {
		Optional<Author> auth = null;
		if (value != null && value.getId() != null) {
			auth = authorService.findById(value.getId());
		}
		return auth == null ? false : auth.isPresent();
	}

}
