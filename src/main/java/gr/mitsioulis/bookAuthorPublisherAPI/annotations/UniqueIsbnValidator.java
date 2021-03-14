package gr.mitsioulis.bookAuthorPublisherAPI.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gr.mitsioulis.bookAuthorPublisherAPI.service.BookService;
import gr.mitsioulis.bookAuthorPublisherAPI.utils.ISBNUtils;

@Component
public class UniqueIsbnValidator implements ConstraintValidator<UniqueISBN, String> {

	@Autowired
	BookService bookService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean valid = true;
		context.disableDefaultConstraintViolation();
		try {
			String normal = ISBNUtils.getStringISBNFromPresentation(value);
			if (!normal.startsWith("978")) {
				valid = false;
				context.buildConstraintViolationWithTemplate("should start with 978").addConstraintViolation();
			}
			if (normal.length() != 13) {
				valid = false;
				context.buildConstraintViolationWithTemplate("must be 13 digits").addConstraintViolation();
			}
			if (bookService.findByISBN(ISBNUtils.getISBNFromPresentation(normal)).isPresent()) {
				valid = false;
				context.buildConstraintViolationWithTemplate("is already registered in another book")
						.addConstraintViolation();
			}
		} catch (NumberFormatException e) {
			valid = false;
			context.buildConstraintViolationWithTemplate("format should of type: XXX-X-XX-XXXXXX-X")
					.addConstraintViolation();
		}

		return valid;
	}

}
