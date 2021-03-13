package gr.mitsioulis.bookAuthorPublisherAPI.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gr.mitsioulis.bookAuthorPublisherAPI.service.BookService;

@Component
public class UniqueIsbnValidator implements ConstraintValidator<UniqueISBN, String> {

	@Autowired
	BookService bookService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return bookService.findByISBN(value).isPresent();
	}

}
