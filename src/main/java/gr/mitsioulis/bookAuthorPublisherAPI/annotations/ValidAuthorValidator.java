package gr.mitsioulis.bookAuthorPublisherAPI.annotations;

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

	@Override
	public boolean isValid(Author value, ConstraintValidatorContext context) {
		boolean valid = true;
		context.disableDefaultConstraintViolation();

		if (!authorService.findById(value.getId()).isPresent()) {
			valid = false;
			context.buildConstraintViolationWithTemplate("no author with id: " + value.getId()).addConstraintViolation();
		}
		return valid;
	}

}
