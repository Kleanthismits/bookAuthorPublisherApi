package gr.mitsioulis.bookAuthorPublisherAPI.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gr.mitsioulis.bookAuthorPublisherAPI.service.BookService;
import gr.mitsioulis.bookAuthorPublisherAPI.utils.ISBNUtils;

@Component
public class ValidIsbnValidator implements ConstraintValidator<ValidISBN, String> {

	@Autowired
	BookService bookService;

	private Boolean isOptional;
	private Boolean checkDuplicate;

	@Override
	public void initialize(ValidISBN validIsbn) {
		this.isOptional = validIsbn.optional();
		this.checkDuplicate = validIsbn.checkDuplicate();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (isOptional && (value == null || value.isBlank())) {
			return true;
		}
		boolean validIsbn = isValidIsbn(value, context);

		return validIsbn;
	}

	private boolean isValidIsbn(String value, ConstraintValidatorContext context) {
		String normal = null;
		boolean valid = true;
		if (value != null) {
			context.disableDefaultConstraintViolation();
			try {
				normal = ISBNUtils.getStringISBNFromPresentation(value);
				// force parsing to long to see if ISBN has the correct format
				ISBNUtils.getISBNFromPresentation(normal);
				if (!normal.startsWith("978")) {
					valid = false;
					context.buildConstraintViolationWithTemplate("should start with 978").addConstraintViolation();
				}
				if (normal.length() != 13) {
					valid = false;
					context.buildConstraintViolationWithTemplate("must be 13 digits").addConstraintViolation();
				}
				if (checkDuplicate && bookService.findByISBN(ISBNUtils.getISBNFromPresentation(normal)).isPresent()) {
					valid = false;
					context.buildConstraintViolationWithTemplate("is already registered in another book")
							.addConstraintViolation();
				}
			} catch (NumberFormatException e) {
				valid = false;
				context.buildConstraintViolationWithTemplate("format should of type: XXX-X-XX-XXXXXX-X")
						.addConstraintViolation();
			}
		}
		return valid;
	}

}
